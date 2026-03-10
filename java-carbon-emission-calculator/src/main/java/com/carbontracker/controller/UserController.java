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

                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setCountry(country);

                boolean saved = repo.saveUser(user);

                if (saved) {
                    response.getWriter().println("User Registered Successfully");
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