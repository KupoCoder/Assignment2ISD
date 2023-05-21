
<%
	int shipmentId = Integer.parseInt(request.getParameter("shipmentId"));
	ShipmentDAO shipmentDAO = new ShipmentDAO();
	shipmentDAO.delete(shipmentId);
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>

</body>
</html>
