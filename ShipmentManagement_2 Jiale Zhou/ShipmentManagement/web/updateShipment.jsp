
<%
	int shipmentId = Integer.parseInt(request.getParameter("shipmentId"));
	int orderId = Integer.parseInt(request.getParameter("orderId"));
	String shipmentMethod = request.getParameter("shipmentMethod");
	String shipmentDate = request.getParameter("shipmentDate");
	String address = request.getParameter("address");

	Shipment shipment = new Shipment();
	shipment.setShipmentId(shipmentId);
	shipment.setOrderId(orderId);
	shipment.setShipmentMethod(shipmentMethod);
	shipment.setShipmentDate(shipmentDate);
	shipment.setAddress(address);

	ShipmentDAO shipmentDAO = new ShipmentDAO();
	shipmentDAO.update(shipment);
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<main class="main-content">
		<%
		shipment shipment = (shipment) session.getAttribute("shipment");
		String updated = (String) session.getAttribute("updated");
		%>
<h1>Shipment Information</h1>
<form method="post" action="">
	<table>
		<tr><td>ShipmentID</td><td><input type="text" name="shipmentID" value="${shipment.shipmentID}" readonly></td></tr>
		<tr><td><input type="hidden"></td><td><input type="hidden" name="userID" value="${shipment.userID}" readonly></td></tr>
		<tr><td>Email</td><td><input type="email" name="email" value="${shipment.email}"></td></tr>
		<tr><td>Phone_number</td><td><input type="text" name="phone" value="${shipment.phone_number}"></td></tr>
		<tr><td>Address</td><td><input type="text" name="address" value="${shipment.address}"></td></tr>
		<tr><td>City</td><td><input type="text" name="city" value="${shipment.city}"></td></tr>
		<tr><td>Post_code</td><td><input type="text" name="post_code" value="${shipment.post_code}"></td></tr>
		<tr><td>StartDate</td><td><input type="date" name="startDate" value="${shipment.startDate}"></td></tr>
		<tr><td>ShipmentMethod</td><td><input type="text" name="shipmentMethod" value="${shipment.shipmentMethod}"></td></tr>
		<tr><td>CurrentStatus</td><td><input type="text" name="currentStatus" value="${shipment.currentStatus}" readonly></td></tr>
		<tr>
			<td>
				<a class ="button" href="">Back</a></td>
			<td><input class ="button" type="submit" value="Update"></td>
		</tr>
	</table>
</form>
</main>
</body>
</html>
