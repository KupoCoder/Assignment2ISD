<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="DB.*"%>
<%@page import="controller.*"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body>
        
        <%  
            //Page Error variables
            
            String userNameErr = (String) session.getAttribute("userNameErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String firstNameErr = (String) session.getAttribute("firstNameErr");
            String lastNameErr = (String) session.getAttribute("lastNameErr");
            String passwordErr = (String) session.getAttribute("passwordErr");
            String DOBErr = (String) session.getAttribute("DOBErr");
            
        %>

        <header class="header">
            <nav class="navbar">
                <a href="index.jsp"> Home </a>
                <% if (session.getAttribute("staff") != null) { %>
                <a class = "active" href='CustomerManager.jsp'> Manage Customers</a>
                <a href ='Catalogue.jsp'> Manage Inventory </a>
                <% } %>
                
                <% if (session.getAttribute("admin") != null) { %>
                <a  href='StaffManager.jsp'> Manage Staff Member</a>
                <a class = "active" href='CustomerManager.jsp'> Manage Customer</a>
                <a href ='Catalogue.jsp'> Manage Inventory</a>
                   
                <% } %>
                
                <% if (session.getAttribute("staff") != null || session.getAttribute("customer") != null || session.getAttribute("admin") != null) { %>
                <a href = 'edit.jsp'> My Account </a>
                <% } %> 
                <a href ='logout.jsp'>Logout</a>
            </nav>
        </header>

        <main class="main">
            <section class="section-form">
                <h1 class="form-title">
                    <% if(session.getAttribute("staff") == null){ %>
                        To Register: Enter your customer details
                    <% } else { %>
                        Register a new customer
                        <a class="button button-inline" href="CustomerManager.jsp"> Return </a>
                    <% } %>
                </h1>
                <form class="form" action="RegisterCustomerController" method = "POST">  
                    <div class="form-group">
                        <label for = "email">Email:</label>
                        <input type = "email" id = "email" name = "email" value="" class="form-input">
                        <span class="form-error"> <%=(emailErr != null ? emailErr : "")%> </span>
                    </div>

                    <div class="form-group">
                        <label for = "userName">User Name: </label>
                        <input id = "userName" type = "text" name = "userName" value="" class="form-input">
                        <span class="form-error"> <%=(userNameErr != null ? userNameErr : "")%> </span>
                    </div>

                    <div class="form-group">
                        <label for = "firstName">First Name: </label>
                        <input id = "firstName" type = "text" name = "firstName" value="" class="form-input">
                        <span class="form-error"> <%=(firstNameErr != null ? firstNameErr : "")%> </span>
                    </div>

                    <div class="form-group">
                        <label for = "lastName">Last Name: </label>
                        <input type = "text" id = "lastName" name ="lastName" value="" class="form-input">
                        <span class="form-error"> <%=(lastNameErr != null ? lastNameErr : "")%> </span>
                    </div>

                    <div class="form-group">
                        <label for = "password">Password: </label>
                        <input type = "password" id = "password" name ="password" value="" class="form-input">
                        <span class="form-error"> <%=(passwordErr != null ? passwordErr : "")%> </span>
                    </div>

                    <div class="form-group">
                        <label for="DOB">Date Of Birth </label>
                        <input type = "date" onfocus="this.showPicker()" id = "DOB" name ="DOB" value="1995-12-12" class="form-input">
                        <span class="form-error"> <%=(DOBErr != null ? DOBErr : "")%> </span>
                    </div>

                    <div class="form-group">
                        <input class="button button-block" type="submit" value="Update">
                    </div>
                </form>
            </section>
        </main>
    </body>
</html>
