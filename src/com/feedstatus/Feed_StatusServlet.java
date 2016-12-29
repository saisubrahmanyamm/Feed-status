package com.feedstatus;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class Feed_StatusServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html");

		boolean isValidEmail = false;
		boolean isValidPassword = true;

		PrintWriter out = resp.getWriter();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("LoginDetails");
		PreparedQuery pq = datastore.prepare(q);
		String loginName = req.getParameter("email");
		String loginPassword = req.getParameter("password");
		String foundName;
		String foundPassword;

		for (Entity d : pq.asIterable()) {
			foundName = d.getProperty("email").toString();
			foundPassword = d.getProperty("password").toString();
//			System.out.println("found user::" + foundName);
//			System.out.println("found pass:" + foundPassword);

			if (loginName.equals(foundName)) {
				isValidEmail = true;

				if (loginPassword.equals(foundPassword)) {
					HttpSession session = req.getSession();
					session.setAttribute("name", loginName);
					RequestDispatcher rd = req.getRequestDispatcher("home.html");
					rd.forward(req, resp);
				} else {
					isValidPassword = false;
				}
			}
		}

		if (isValidEmail == false) {
			req.getRequestDispatcher("index.html").include(req, resp);
			out.println("<h2 style = color:#B3008E;>Your entered Email id is wrong</h2>");
		} else if (isValidPassword == false) {
			req.getRequestDispatcher("index.html").include(req, resp);

			out.println("<h2 style = color:#B3008E;>Your entered Password is wrong</h2>");
		}
	}

}
