package com.feedstatus;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Signup extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		String name = req.getParameter("FirstName");

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity employee = new Entity("LoginDetails");
		employee.setProperty("email", req.getParameter("Email"));
		employee.setProperty("password", req.getParameter("Password"));
		employee.setProperty("firstname", req.getParameter("FirstName"));
		employee.setProperty("lastname", req.getParameter("LastName"));
		employee.setProperty("dateofbirth", req.getParameter("DOB"));
		employee.setProperty("gender", req.getParameter("Gender"));
		employee.setProperty("MobileNumber", req.getParameter("Mobile"));
		datastore.put(employee);

		req.getRequestDispatcher("index.html").include(req, res);
		out.println("Successfully Registered Dear " + name);

		res.setContentType("text/html");
	}

}
