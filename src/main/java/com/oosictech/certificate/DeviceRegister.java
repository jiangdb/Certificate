package com.oosictech.certificate;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JDB on 2014/10/25.
 */
public class DeviceRegister extends HttpServlet {
    private String dburl;
    private String user;
    private String passwd;

    public void init() throws ServletException {
        String driverClass=getInitParameter("driverClass");
        dburl = getInitParameter("url");
        user = getInitParameter("name");
        passwd = getInitParameter("pass");
        try {
            Class.forName(driverClass);
        }
        catch (ClassNotFoundException ce){
            throw new UnavailableException("Load database server error!");
        }
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException
    {
        Connection conn=null;
        Statement stmt=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        String uuid = req.getParameter("UUID");
        String model = req.getParameter("MODEL");
        if (null == uuid || uuid.equals("")){
            out.println("{\"result\": \"fail\",\"message\": \"Error uuid is empty\"}");
            out.close();
            return;
        }
        if (null == model || model.equals("")){
            out.println("{\"result\": \"fail\",\"message\": \"Error model is empty\"}");
            out.close();
            return;
        }
        try{
            //long startTime=System.currentTimeMillis();
            conn = DriverManager.getConnection(dburl, user, passwd);
            stmt = conn.createStatement();
            stmt.executeUpdate("use CarDevices");
            rs = stmt.executeQuery("select * from " + model + " where uuid = \"" + uuid + "\"");
            if (rs.next()) {
                out.println("{\"result\": \"success\",\"message\": \"uuid " + uuid +" is already registered on " + rs.getString("created") +"\"}");
            }else {
                stmt.execute("insert into " + model + " value(null, \""+uuid+"\", null)");
                out.println("{\"result\": \"success\",\"message\": \"register success\"}");
            }
            //long endTime=System.currentTimeMillis();
            //out.println("{\"timeused\": \"" + (endTime-startTime) + "ms\"}");
        }
        catch (SQLException se){
            se.printStackTrace();
        }finally {
            out.close();
            if (stmt!=null){
                try {
                    stmt.close();
                }catch (SQLException se) {
                    se.printStackTrace();
                }
                stmt = null;
            }
            if (rs!=null){
                try {
                    rs.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
                rs = null;
            }
            if (conn!=null){
                try {
                    conn.close();
                }catch (SQLException se) {
                    se.printStackTrace();
                }
                conn=null;
            }
        }
    }
}
