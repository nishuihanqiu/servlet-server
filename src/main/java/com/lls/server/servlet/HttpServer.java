package com.lls.server.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/************************************
 * HttpServer
 * @author liliangshan
 * @date 2019-08-20
 ************************************/
public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class.getSimpleName());

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;
    private ServerSocket serverSocket = null;

    private void createSocket() {
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
            logger.info("create socket success.");
        } catch (IOException e) {
            logger.error("create socket error: {}", e.getMessage());
            System.exit(1);
        }
    }

    private void await() {
        while (!shutdown) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                HttpRequest request = new HttpRequest(inputStream);
                request.parse();

                HttpResponse response = new HttpResponse(outputStream);
                response.setRequest(request);

                if (request.getUri().startsWith("/servlet")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                logger.error("await server error:{}", e.getMessage());
                System.exit(1);
            }
        }

    }

    public void start() {
        logger.info("starting server .");
        this.createSocket();
        this.await();
    }

}
