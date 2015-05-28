<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>车机后台登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/login2.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    String user = (String)session.getAttribute("user");
    if(user!=null)
    {
        response.sendRedirect("devices");
    }
%>
<h1>后台登录<sup>V2014</sup></h1>

<div class="login" style="margin-top:50px;">

    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">

        <!--登录-->
        <div class="web_login" id="web_login">

            <div class="login-box">
                <div class="login_form">
                    <form action="login" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>
                            <div class="inputOuter" id="uArea">
                                <input id="u" name="username" class="inputstyle" type="text">
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="p">密码：</label>
                            <div class="inputOuter" id="pArea">
                                <input id="p" name="pass" class="inputstyle" type="password">
                            </div>
                        </div>
               
                        <div style="padding-left:50px;margin-top:20px;"><input value="登 录" style="width:150px;" class="button_blue" type="submit"></div>
                    </form>
                </div>
           
            </div>
               
        </div>
        <!--登录end-->
    </div>
</div>

</body></html>