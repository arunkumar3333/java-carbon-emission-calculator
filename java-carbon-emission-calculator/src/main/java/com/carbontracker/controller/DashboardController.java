package com.carbontracker.controller;

import com.carbontracker.repository.CarbonRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/dashboard/emission")
public class DashboardController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CarbonRepository repo = new CarbonRepository();

        List<Object[]> data = repo.getCategoryWiseEmission();

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        out.print("[");

        for (int i = 0; i < data.size(); i++) {

            Object[] row = data.get(i);

            out.print("{\"vehicle\":\"" + row[0] + "\",\"emission\":" + row[1] + "}");

            if (i < data.size() - 1) {
                out.print(",");
            }
        }

        out.print("]");
    }
}