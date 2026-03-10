package com.carbontracker.controller;

import com.carbontracker.repository.CarbonRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/dashboard/total")
public class TotalEmissionController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userId") == null){
            response.sendRedirect("login.html");
            return;
        }

        Long userId = (Long) session.getAttribute("userId");

        CarbonRepository repo = new CarbonRepository();

        Double total = repo.getTotalEmission(userId);

        response.setContentType("application/json");

        response.getWriter().print("{\"total\":" + total + "}");
    }
}