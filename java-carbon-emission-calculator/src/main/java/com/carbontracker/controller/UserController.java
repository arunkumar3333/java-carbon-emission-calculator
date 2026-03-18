package com.carbontracker.controller;

import com.carbontracker.entity.User;
import com.carbontracker.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");

        String action = request.getParameter("action");

        UserRepository repo = new UserRepository();

        try {

            /* =========================
               REGISTER
            ========================= */
            if ("register".equals(action)) {
            		
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String country = request.getParameter("country");
// object creation
                User user = new User();  //which is used to insert data into db
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setCountry(country);
// created new variable called saved which is type of boolean
                boolean saved = repo.saveUser(user); // to save the new user into db

                if (saved) {
                    response.getWriter().println("User Registered Successfully");  // its sends the response to app.js
                } else {
                    response.getWriter().println("Registration Failed");
                }
            }

            /* =========================
               LOGIN
            ========================= */
            else if ("login".equals(action)) {

                String email = request.getParameter("email");
                String password = request.getParameter("password");

                User user = repo.loginUser(email, password);

                if (user != null) {

                    HttpSession session = request.getSession();
                    session.setAttribute("userId", user.getId());

                    response.getWriter().println("Login Success");

                } else {

                    response.getWriter().println("Invalid Credentials");

                }
            }

            /* =========================
               LOGOUT
            ========================= */
            else if ("logout".equals(action)) {

                HttpSession session = request.getSession(false);

                if (session != null) {
                    session.invalidate();
                }

                response.getWriter().println("Logout Success");
            }

            else {
                response.getWriter().println("Invalid Action");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Server Error");
        }
    }
}