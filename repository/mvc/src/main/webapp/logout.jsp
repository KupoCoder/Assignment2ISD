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
        <% //logout logic 
            String LogId = (String) session.getAttribute("SessionLogId");
            DBManager manager = (DBManager) session.getAttribute("manager");
            manager.updateAccessLog(LogId);
        %>

        <header class="header">
            <nav class="navbar">
                <a href="index.jsp">Home</a> 
                <a class="active" href="logout.jsp">Logout</a>
            </nav>
        </header>

        <main class="main">
            <div class="section-form">
                <h2 class="form-title">Logged Out Successfully</h2>
                <p>You have been logged out. Click below to go back to the home page.</p>
                <a href="index.jsp" class="button">Home</a>
            </div>
        </main>

        <% session.invalidate();%>
    </body>
</html>
