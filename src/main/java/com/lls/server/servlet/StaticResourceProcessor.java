package com.lls.server.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/************************************
 * StaticResourceProcessor
 * @author liliangshan
 * @date 2019-08-20
 ************************************/
public class StaticResourceProcessor {

    private static final Logger logger = LoggerFactory.getLogger(StaticResourceProcessor.class.getSimpleName());

    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            logger.error("send static resource error:{}", e.getMessage());
        }
    }
}
