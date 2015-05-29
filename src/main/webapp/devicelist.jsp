<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 2015/5/25
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.oosictech.certificate.Device" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>车机后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/devicelist.css" rel="stylesheet" type="text/css">
    <script type="application/javascript">
        function GetUrlParms()
        {
            var args=new Object();
            var url=location.search;
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                var params = str.split("&");
                for (var i=0;i<params.length;i++) {
                    var pos=params[i].indexOf('=');//查找name=value
                    if(pos==-1)   continue;//如果没有找到就跳过
                    var argname=params[i].substring(0,pos);//提取name
                    var value=params[i].substring(pos+1);//提取value
                    args[argname]=unescape(value);//存为属性
                }
            }
            return args;
        }
    </script>
</head>
<body>
    <div class="header">
        <h1>车机后台管理系统</h1>
    </div>
    <div id="sepLineTd" class="topline_height">
        <div class="topline">
            <div id="imgLine" class="toplineimg left"></div>
        </div>
    </div>
  <div id="leftPanel">
      <div id="nav_midbar" class="listbg listflow">
          <ul class="nav_menu">
              <li id="TC_TGLS05" class="model"><a href="devices?model=TC_TGLS05&pagenum=1">TC_TGLS05</a></li>
              <li id="TC_TGLS05_KO" class="model"><a href="devices?model=TC_TGLS05_KO&pagenum=1">TC_TGLS05_KO</a></li>
              <li id="xxgd_res_2.0" class="model"><a href="devices?model=xxgd_res_2.0&pagenum=1">xxgd_res_2.0</a></li>
              <li id="xxgd_res_2.0_ko" class="model"><a href="devices?model=xxgd_res_2.0_ko&pagenum=1">xxgd_res_2.0_ko</a></li>
          </ul>
          <script type="application/javascript">
              var args = new Object();
              args = GetUrlParms();
              var model = args["model"];
              document.getElementById(model).style.backgroundColor = "#1E5494";
              document.getElementById(model).firstChild.style.color = "#FFFFFF";
          </script>
      </div>
      <div id="nav_bottombar" class="navbottom"></div>
  </div>

  <div id="rightPanel">
      <div id="right_top">
          <div id="left" class="div_inline">
              <h2 class="div_inline table_header"><%=request.getAttribute("model")%>(Total: <%=request.getAttribute("totalcount")%>)</h2>
          </div>
          <div id="right" class="div_inline search">
              <form class="div_inline" name="search" action="devices" method="get">
                  <input name="search_string" type="text" style="width: 500px;" />
                  <input type="submit" value="search"/>
              </form>
          </div>
      </div>
      <div id="right_mid">
          <table class="device_table">
              <tr>
                  <th class="device_id" style="width: 20px;">id</th>
                  <th class="device_uuid">uuid</th>
                  <th class="device_createtime">createtime</th>
                  <th class="device_createip">createip</th>
              </tr>
              <%
                  List<Device> devicelist = (List<Device>) request.getAttribute("list");
                  for (Device d : devicelist) {
                      if (d != null) {
                          out.println("<tr class=\"device_item\">");
                          out.println("<td class=\"device_id\" style=\"width: 20px\">" + d.getId() + "</td>");
                          out.println("<td class=\"device_uuid\">" + d.getUUID() + "</td>");
                          out.println("<td class=\"device_createtime\">" + d.getCreateTime() + "</td>");
                          out.println("<td class=\"device_createip\">" + d.getCreateIp() + "</td>");
                          out.println("</tr>");
                      }
                  }
              %>
          </table>
      </div>
      <div id="right_bottom">
          <div class="page_foot">
              <%
                  Integer tp = (Integer)request.getAttribute("totalpage");
                  if (tp > 1) {
                      Integer curpg = (Integer) request.getAttribute("curpage");
                      int startpg = (curpg / 10) * 10 + 1;
                      int endpg = (startpg + 9) > tp ? tp : (startpg + 9);
                      out.println("<a class=\"symbol\" href=devices?pagenum=1>first</a>");
                      if (curpg != 1) {
                          out.println("<a class=\"symbol\" href=devices?pagenum=" + (curpg - 1) + "> &lt </a>");
                      }
                      if (startpg > 1) {
                          out.println("<a class=\"symbol\" href=devices?pagenum=" + (startpg - 10) + "> ... </a>");
                      }
                      for (int i = startpg; i <= endpg; i++) {
                          if (i == curpg) {
                              out.println("<a href=devices?pagenum=" + i + "> <strong>" + i + "</strong></a>");
                          } else {
                              out.println("<a href=devices?pagenum=" + i + ">" + i + "</a>");
                          }
                      }
                      if (tp > endpg) {
                          out.println("<a class=\"symbol\" href=devices?pagenum=" + (endpg + 1) + "> ... </a>");
                      }
                      if (curpg != tp) {
                          out.println("<a class=\"symbol\" href=devices?pagenum=" + (curpg + 1) + "> &gt </a>");
                      }
                      out.println("<a class=\"symbol\" href=devices?pagenum=" + tp + ">last</a>");
                  }
              %>
          </div>
      </div>
  </div>
</body>
</html>
