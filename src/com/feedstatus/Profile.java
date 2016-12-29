package com.feedstatus;

import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
public class Profile extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                      throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
         
          
        HttpSession session=request.getSession(false);  
        if(session!=null){  
        String name=(String)session.getAttribute("name");  
        request.getRequestDispatcher("profile.html").include(request, response);  
          
       
        }  
        else{    
            request.getRequestDispatcher("index.html").include(request, response);  
			out.println("<h2 style = color:#B3008E;>Please login first </h2>");
        }  
        out.close();  
    }  
}  