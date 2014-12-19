package com.oosictech.certificate;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Home on 2014/10/26.
 */
public class CreateDevices extends HttpServlet {
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
        try{
            long startTime=System.currentTimeMillis();
            conn = DriverManager.getConnection(dburl, user, passwd);
            stmt = conn.createStatement();
            stmt.executeUpdate("use test");
            pstmt = conn.prepareStatement("insert into testtable values(?,?,?)");
            for (int i=0;i<100000;i++) {
                pstmt.setString(1,null);
                pstmt.setString(2,"386645"+String.format("%06x",i));
                pstmt.setString(3,null);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            pstmt.clearBatch();
            long endTime=System.currentTimeMillis();
            out.println("{\"result\": \"success\",\"message\": \"register success in " + (endTime-startTime) + "ms\"}");
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
