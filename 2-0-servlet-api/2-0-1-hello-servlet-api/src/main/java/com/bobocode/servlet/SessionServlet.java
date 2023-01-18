package com.bobocode.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var dummySessionStorage =  DummySessionStorage.init();
        var sessionMap = dummySessionStorage.getSession(request, response);
        String cookieName = "name";
        if (sessionMap.get(cookieName) != null) {
            response.getWriter().println("Hello, " + sessionMap.get(cookieName));
        } else if (request.getParameterMap().get(cookieName) != null){
            var nameArray = request.getParameterMap().get(cookieName);
            if(nameArray.length != 0) {
                response.getWriter().println("Hello, " + nameArray[0]);
                sessionMap.put(cookieName, nameArray[0]);
            }
        } else {
            response.getWriter().println("Hello!");
        }
    }
}
