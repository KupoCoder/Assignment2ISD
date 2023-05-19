<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="controller.*"%>
<%@page import="DB.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Edit</title>
    </head>
    <body>
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            Staff staff = (Staff) session.getAttribute("staff");
            Admin admin = (Admin) session.getAttribute("admin");

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
            String postCodeErr = (String) session.getAttribute("postCodeErr");
        %>

        <!-- Navigation and top banner code here -->

        <div class="contentcontainer">
            <br>
            <h1 id="underlineandcenter">Edit your current details</h1>
            
            <!-- Placeholder buttons -->
            <!-- Access log and payment details code here -->

            <% if(staff != null || customer != null){ %>
            <form action="editAccount" method = "POST">  
                <table>
                    <tr>
                        <td><label for = "email">Email:</label></td>
                        <td><input type = "email" id = "email" name = "email" value="${(customer!=null) ? customer.getEmail() : staff.getStaffEmail()}"><small> <%= (emailErr != null ? emailErr : "") %> </small></td>
                    </tr>
                    <tr>
                        <td><label for = "userName">User Name: </label></td>
                        <td><input id = "userName" type = "text" name = "userName" value="${(customer!=null) ? customer.getUserName() : staff.getStaffUsername()}"><small> <%= (userNameErr != null ? userNameErr : "") %> </small></td>
                    </tr>
                    <!-- Further fields as per your requirements -->
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Update">
                        </td>
                    </tr>
                </table>  
            </form>
            <% } %>
            
            <% if(admin != null){ %>
            <form action="editAccount" method = "POST"> 
                <table>
                    <tr>
                        <td><label for = "Ademail">Email:</label></td>
                        <td><input type = "email" id = "Ademail" name = "Ademail" value="${admin.getAdminEmail()}"><small> <%= (emailErr != null ? emailErr : "") %> </small></td>
                    </tr>
                    <!-- Further fields as per your requirements -->
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Update">
                        </td>
                    </tr>
                </table>
            </form>
            <% } %>

        </div>
    </body>
</html>
