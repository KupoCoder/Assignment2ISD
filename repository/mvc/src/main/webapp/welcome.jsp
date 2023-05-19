<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="DB.*"%>
<%@page import="controller.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Welcome</title>
    </head>


    <body class = "welcome">
        <div class="topbanner"></div>

        <div class="topnav">
            <a  class="active" href="index.jsp"> Home </a>
            <% if (session.getAttribute("staff") == null && session.getAttribute("customer") == null && session.getAttribute("admin") == null) { %>
            <a href="register.jsp"> Register </a>
            <a href="login.jsp"> Login </a>
            <% }%> 
            <% if (session.getAttribute("staff") != null) { %>
            <a href='CustomerManager.jsp'> Manage Customers</a>
            <a href ='Catalogue.jsp'> Manage Inventory </a>
            <% } %>
            <% if (session.getAttribute("admin") != null) { %>
            <a href='StaffManager.jsp'> Manage Staff members</a>
            <a href='CustomerManager.jsp'> Manage Customers</a>
            <a href ='Catalogue.jsp'> Manage Inventory</a>
            <% } %>

            <%if (session.getAttribute("staff") == null && session.getAttribute("admin") == null) { %>
            <a  href ='Catalogue.jsp'>Catalogue</a>
            <% }%>
            <% if (session.getAttribute("staff") != null || session.getAttribute("customer") != null || session.getAttribute("admin") != null) { %>
            <a href = 'edit.jsp'> my account </a>
            <a  href="logout.jsp"  >Logout</a>
            <% }%> 
        </div>

            <div class="contentcontainer">
                
                <%
                    Customer customer = (Customer) session.getAttribute("customer");
                    
                %>

                <% if (session.getAttribute("staff") == null && session.getAttribute("admin") == null) { %>
                <h1 id="underlineandcenter">Welcome ${customer.firstName} !</h1><br>
                <h3> Your email is ${customer.email}</h3>
                <h3> And your user name is ${customer.userName}</h3>

                <h3>Use this button to access the main page: </h3><a href="mainpage.jsp" class="button" >Main Page</a>
                <%
                } else if(session.getAttribute("staff") != null) {
                    customer = (Customer) session.getAttribute("editCus");
                %>
                <h1 id="underlineandcenter"> New Customer created!</h1><br>
                    <a href="register.jsp"> Make another Customer</a>
                    <a href="CustomerManager.jsp"> Return </a>
                <% } else if (session.getAttribute("admin") != null){%>
                
                    <h1 id="underlineandcenter"> New Customer created!</h1><br>
                    <a href="register.jsp"> Make another Customer</a>
                    <a href="CustomerManager.jsp"> Return </a>
                <% } %>

            </div>


    </body>
</html>
