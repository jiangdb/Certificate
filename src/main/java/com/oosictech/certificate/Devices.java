package com.oosictech.certificate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Home on 2015/5/27.
 */
@WebServlet(name = "Devices")
public class Devices extends HttpServlet {
    private static int ROW_PER_PAGE = 50;
    private String dburl;
    private String dbuser;
    private String dbpasswd;
    private String driverClass;
    private static String modellist[] = {
            "TC_TGLS05",
            "TC_TGLS05_KO",
            "xxgd_res_2.0",
            "xxgd_res_2.0_ko"
    };

    public void init() throws ServletException {
        driverClass=getInitParameter("driverClass");
        dburl = getInitParameter("url");
        dbuser = getInitParameter("name");
        dbpasswd = getInitParameter("pass");
    }

    private void handleSearch(String search, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeviceDao dao = new DeviceDao(dburl,dbuser,dbpasswd,driverClass);
        List<Device> list = dao.getDevices(search);
        int totalcount = list.size();

        int pos;
        String model = "unkown";
        for (String s:modellist) {
            pos = search.indexOf(s);
            if (pos != -1) {
                model = s;
            }
        }

        request.setAttribute("totalpage", 1);
        request.setAttribute("totalcount", totalcount);
        request.setAttribute("curpage", 1);
        request.setAttribute("model", model);
        request.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("devicelist.jsp");
        rd.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("user");
        if (null == user || "".equals(user)) {
            response.sendRedirect("login.jsp");
            return;
        }

        String model = request.getParameter("model");
        String pagenum = request.getParameter("pagenum");
        if (model == null || model.equals("")) {
            model = "TC_TGLS05";
        }
        if (pagenum == null || pagenum.equals("")) {
            pagenum = "1";
        }

        //check if it is search
        String search = request.getParameter("search_string");
        if (search!=null && !"".equals(search)) {
            handleSearch(search, request, response);
            return;
        }

        DeviceDao dao = new DeviceDao(dburl, dbuser,dbpasswd,driverClass);
        int totalcount = dao.getDevicesCount(model);
        int totalpage = totalcount/ROW_PER_PAGE;
        if (totalcount % ROW_PER_PAGE != 0) {
            totalpage++;
        }
        int curpage = Integer.valueOf(pagenum);

        List<Device> list = dao.getDevices(model,(curpage-1)*ROW_PER_PAGE, ROW_PER_PAGE);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("totalcount", totalcount);
        request.setAttribute("curpage", curpage);
        request.setAttribute("model", model);
        request.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("devicelist.jsp");
        rd.forward(request,response);
    }
}
