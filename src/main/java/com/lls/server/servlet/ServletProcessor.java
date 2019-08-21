package com.lls.server.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/************************************
 * ServletProcessor
 * @author liliangshan
 * @date 2019-08-20
 ************************************/
public class ServletProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ServletProcessor.class.getSimpleName());

    public void process(HttpRequest request, HttpResponse response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader classLoader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classpath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null,
                    classpath.getCanonicalPath()+ File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            classLoader = new URLClassLoader(urls);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        Class clz = null;
        try {
            clz = classLoader.loadClass(PrimitiveServlet.class.getName());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        Servlet servlet = null;
        try {
            if (clz != null) {
                servlet = (Servlet) clz.newInstance();
                servlet.service(request, response);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
