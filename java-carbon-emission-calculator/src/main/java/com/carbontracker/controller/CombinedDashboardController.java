package com.carbontracker.controller;
import javax.servlet.ServletException;
import com.carbontracker.repository.CarbonRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/dashboard/combined")
public class CombinedDashboardController extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	 HttpSession session = request.getSession(false);

	    if(session == null || session.getAttribute("userId") == null){
	        response.sendRedirect("login.html");
	        return;
	    }
	    Long userId = (Long) session.getAttribute("userId");
CarbonRepository repo = new CarbonRepository();

List<Object[]> data = repo.getCombinedEmission(userId);

response.setContentType("application/json");

PrintWriter out = response.getWriter();

out.print("[");

for(int i=0;i<data.size();i++){

Object[] row = data.get(i);

out.print("{\"vehicle\":\""+row[0]+"\",\"emission\":"+row[1]+"}");

if(i < data.size()-1){
out.print(",");
}

}

out.print("]");

}
}