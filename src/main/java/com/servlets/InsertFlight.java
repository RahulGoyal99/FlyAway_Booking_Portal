package com.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.Dao;


@WebServlet("/InsertFlight")
public class InsertFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String fromf=request.getParameter("from");
		String tof=request.getParameter("to");
		String datef=request.getParameter("departure");
		String timef=request.getParameter("time");
		String price=request.getParameter("price");
		
		HashMap<String,String> flight=new HashMap<>();
		flight.put("name", name);
		flight.put("from", fromf);
		flight.put("to", tof);
		flight.put("date", datef);
		flight.put("time", timef);
		flight.put("price", price);
		
		try {
			Dao dao=new Dao();
			HttpSession session=request.getSession();
			
			if(dao.insertFlight(flight)) {
				
				session.setAttribute("message", "Flight Added Sucessfully");
			}
			else {
				session.setAttribute("message", "Invalid Details");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("error");
			e.printStackTrace();
		}
		response.sendRedirect("InsertFlight.jsp");
		
	}

}
