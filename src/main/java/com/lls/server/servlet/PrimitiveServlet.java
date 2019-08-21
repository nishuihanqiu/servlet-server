package com.lls.server.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/************************************
 * PrimitiveServlet
 * @author liliangshan
 * @date 2019-08-20
 ************************************/
public class PrimitiveServlet implements Servlet {

    private static final Logger logger = LoggerFactory.getLogger(PrimitiveServlet.class.getSimpleName());

    public PrimitiveServlet() {
        try {
            this.init(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("init....");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("service....");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("hello roses are red.");
        writer.println("violets are blue.");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        logger.info("destroy....");
    }


}
