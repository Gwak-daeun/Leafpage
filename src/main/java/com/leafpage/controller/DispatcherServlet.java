package com.leafpage.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

// DispatcherServlet : 브라우저의 모든 요청(*.do)을 받는 프런트 컨트롤러
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    private HandlerMapping handlerMapping;
    private ViewResolver viewResolver;

    public void init() throws ServletException {
        handlerMapping = new HandlerMapping();
        viewResolver = new ViewResolver();
        viewResolver.setPrefix("./WEB-INF/");
        viewResolver.setSuffix(".jsp");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));

        Controller ctrl = handlerMapping.getController(path);

        String viewName = ctrl.handleRequest(request, response);
        if (viewName.equals("none")) {
            return;
        }
        String view = viewResolver.getView(viewName);
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

}