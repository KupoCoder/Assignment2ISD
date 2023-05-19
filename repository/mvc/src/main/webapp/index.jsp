<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="DB.*"%>
<%@page import="controller.*"%>

<!DOCTYPE html>
<html>
    <head>
        <title>IoTBay</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body>
        <div class="topbanner"></div>
<div>
        <div class="topnav">
            <a  class="active" href="index.jsp"> Home </a>
            <% if (session.getAttribute("staff") == null && session.getAttribute("customer") == null && session.getAttribute("admin") == null) { %>
                <a href="register.jsp"> Register </a>
                <a href="login.jsp"> Login </a>
                <a href = "Catalogue.jsp"> Catalogue </a>
            <% }%> 
            <% if (session.getAttribute("staff") != null) { %>
            <a href='CustomerManager.jsp'> Manage Customers</a>
            <a href ='Catalogue.jsp'> Manage Inventory </a>
            <% } %>
            
            <% if (session.getAttribute("customer") != null) { %>
            
            <a href ='Catalogue.jsp'> Catalogue</a>
            
            <% } %>
            
            <% if (session.getAttribute("admin") != null) { %>
            <a href='StaffManager.jsp'> Manage Staff Member</a>
            <a href='CustomerManager.jsp'> Manage Customer</a>
            <a href ='Catalogue.jsp'> Manage Inventory</a>
               
            <% } %> 
            
            
            <% if (session.getAttribute("staff") != null || session.getAttribute("customer") != null || session.getAttribute("admin") != null) { %>
            <a href = 'edit.jsp'> my account </a>
            <a  href="logout.jsp"  >Logout</a>
            <% }%> 
        </div>
    </div>

    <div class="contentcontainer">
        <h1 id="underlineandcenter">Welcome to the IoTBay Store Homepage!</h1><br>

        <h4 id="center">If you are new to our site please select the register option above.</h4>

        <h4 id="center">If you are a returning customer you may select the login option instead.</h4>

    </div>
   
</body>
</html>