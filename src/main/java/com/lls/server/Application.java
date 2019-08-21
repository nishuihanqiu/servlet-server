package com.lls.server;

import com.lls.server.servlet.HttpServer;

/************************************
 * Application
 * @author liliangshan
 * @date 2019-08-20
 ************************************/
public class Application {

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.start();
    }

}
