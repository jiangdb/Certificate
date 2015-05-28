package com.oosictech.certificate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Home on 2014/10/2.
 */
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        String name = req.getParameter("username");
        String pass = req.getParameter("pass");
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        if (name !=null && name.equals("admin")) {
            if (pass != null && pass.equals("OOZICjdb008")) {
                HttpSession session = req.getSession();
                session.setAttribute("user", "admin");
                resp.sendRedirect("devices");
            } else {
                out.println("Wrong pass");
            }
        }else{
            out.println("wrong user");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}