package com.carbontracker.controller;

import com.carbontracker.entity.User;
import com.carbontracker.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/user") //the request goes to UserController.
public class UserController extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("text/plain");

    String action = request.getParameter("action");
    System.out.println("Action received: " + action);

    UserRepository repo = new UserRepository();

    if ("register".equals(action)) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String country = request.getParameter("country");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setCountry(country);

       
        
        boolean saved = repo.saveUser(user);

        if(saved){
            response.getWriter().println("User Registered Successfully");
        }else{
            response.getWriter().println("Registration Failed");
        }
    }

    else if ("login".equals(action)) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean result = repo.loginUser(email, password);

        if(result){
            response.getWriter().println("Login Success");
        }else{
            response.getWriter().println("Invalid Credentials");
        }
    }
}
}



