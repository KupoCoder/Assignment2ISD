
<%
	int shipmentId = Integer.parseInt(request.getParameter("shipmentId"));
	ShipmentDAO shipmentDAO = new ShipmentDAO();
	Shipment shipment = shipmentDAO.read(shipmentId);
%>
<!-- HTML to display shipment details -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>

</body>
</html>
