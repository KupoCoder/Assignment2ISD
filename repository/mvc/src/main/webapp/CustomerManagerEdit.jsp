<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="DB.*"%>
<%@page import="controller.*"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>

        <!-- This is the top nav bar code-->
        <div class="topbanner"></div>
        <div class="topnav">
            
            <% if (session.getAttribute("staff") != null) { %>
            <a href="mainpage.jsp"> Home </a>
            <a class="active" href='CustomerManager.jsp'> Manage Customers</a>
            <a href ='Catalogue.jsp'> Manage Inventory </a>
            <% } %>
            
            <% if (session.getAttribute("admin") != null) { %>
            <a href="mainpage.jsp"> Home </a>
            <a href='StaffManager.jsp'> Manage Staff Member</a>
            <a class = "active" href='CustomerManager.jsp'> Manage Customer</a>
            <a href ='Catalogue.jsp'> Manage Inventory</a>
               
            <% } %> 
            
            <% if (session.getAttribute("staff") != null || session.getAttribute("customer") != null || session.getAttribute("admin") != null) { %>
            <a href = 'edit.jsp'> my account </a>
            <% } %> 
            <%if (session.getAttribute("customer") != null && session.getAttribute("staff") == null && session.getAttribute("admin") ==  null) { %>
            <a href ='Catalogue.jsp'>Catalogue</a>
            <% } %>

            <a  href="logout.jsp" >Logout</a>
        </div>
            
 
                
               <%   ///Page Variables
                    DBManager manager = (DBManager) session.getAttribute("manager");
                    ArrayList<Customer> customers = manager.searchCustomer(request.getParameter("customerSearch"));
                    
                    Customer Editcustomer = (Customer) session.getAttribute("editCustomer");
                           
                    String userNameErr = (String) session.getAttribute("userNameErr");
                    String emailErr = (String) session.getAttribute("emailErr");
                    String firstNameErr = (String) session.getAttribute("firstNameErr");
                    String lastNameErr = (String) session.getAttribute("lastNameErr");
                    String passwordErr = (String) session.getAttribute("passwordErr");
                    String DOBErr = (String) session.getAttribute("DOBErr");
                    String phoneNumErr = (String) session.getAttribute("phoneNumErr");
                    String streetErr = (String) session.getAttribute("streetErr");
                    String suburbErr = (String) session.getAttribute("suburbErr");
                    String stateErr = (String) session.getAttribute("stateErr");
                    String postCodeErr =(String) session.getAttribute("postCodeErr");
                    
                   String deleteErr = (String) session.getAttribute("deleteErr");
               %> 

              <div style = "display:flex">
                 

           <div class ="contentcontainer" >
              
                <br>
                <h1 id="underlineandcenter">Customer List</h1>
                
                <a id = "accessTabs" href = "register.jsp"> Register New customers <a/>
                <h1 id="underlineandcenter"> <%= (deleteErr != null ? deleteErr : "") %> </h1>
                <form  id="underlineandcenter" method = "post">
                    <input id="customerSearch"  type="text" name="customerSearch" placeholder="Search Customers By First Name Last Name or Email">                
                </form>
                <br>
                  
                    <table border='1'>
                    <tr><th>ID</th><th>Name</th><th>Email</th><th>Action</th></tr>
                    
                     <%for(Customer cus: customers) { %>
                        <td><%= cus.getCustomerId() %></td>
                        <td><%= cus.getFirstName() %> <%=cus.getLastName()%></td>
                         <td><%= cus.getEmail() %></td>
                        <!-- Add more columns for other attributes -->
                        <td>
                           <form action="editCustomer" method="post">
                              <input type="hidden" name="custId" id = "custId" value="<%=cus.getCustomerId()%>">
                              <input type="submit" value="Edit">
                             
                           </form>
                              <br>
                               <form action = "DeleteCustomerServlet" method = "post">
                                  <input type="hidden" name="DELcustId" id = "DELcustId" value="<%=cus.getCustomerId()%>">
                                  <input type ="submit" value ="Delete">
                              </form>
                        </td>
                     </tr>
                    <%}%>
             
                
            </div>
         
                </div>
        </div>  
    </body>
</html>
