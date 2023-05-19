<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="DB.*"%>
<%@page import="controller.*"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body >
        <div class="header">
            <div class="navbar">
                <a href="index.jsp"> Home </a>
                <a href="register.jsp"> Register </a>
                <a class="active" href="login.jsp"> Login </a>
                <!-- Repeat your conditions here ... -->
            </div>
        </div>

        <%
            String existErr = (String) session.getAttribute("existErr");
            String credentialErr = (String) session.getAttribute("credentialErr");
            String passErr = (String) session.getAttribute("passErr");
        %>

        <div class="main">
            <div class="section-form">
                <h1 class="form-title">Login</h1>
                <h4 class="form-error"><%=(existErr != null ? existErr : "")%></h4>
                
                <form action="LoginServlet" method = "post">
                    <div class="form-group">
                        <label for = "emailuser"> Username or Email:</label>
                        <input type = "text" id = "emailuser" name = "emailuser" class="form-input" placeholder="<%= (credentialErr != null ? credentialErr : "Enter Email or Username")%>">
                        <span class="form-error"><%=credentialErr != null ? credentialErr : ""%></span>
                    </div>

                    <div class="form-group">
                        <label for = "password">Password: </label>
                        <input type = "password" id = "password" name ="password" class="form-input" placeholder="<%= (passErr != null ? passErr : "Enter password")%>">
                        <span class="form-error"><%=passErr != null ? passErr : ""%></span>
                    </div>

                    <div class="form-group">
                        <input type="submit" id="login_btn" class="button" value=" Login ">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
