
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
	shipmentDAO.create(shipment);
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<main class="main-content">
	<%
		String existErr = (String) session.getAttribute("existErr");
		String  intErr  = (String) session.getAttribute("intErr"); //need more validation
	%>
	<h1>New Shipment</h1>
	<form method = "post" action="">

		<label for="name">PName</label><input type="text" name="name" placeholder="Enter name">
		<label for="name">Email</label><input type="email" name="email" placeholder="Enter email">
		<label for="name">Phone_number</label><input type="text" name="phone" placeholder="<%= (intErr != null ? intErr :"Enter phone")%>">
		<label for="name">Address</label><input type="text" name="address" placeholder="Enter address">
		<label for="name">City</label><input type="text" name="city" placeholder="Enter city">
		<label for="name">Post_code</label><input type="text" name="post_code" placeholder="">
		<label for="name">ShipmentMethod</label><input type="text" name="shipmentMethod" placeholder="">
		<input class="button" type ="submit" value="submit">


	</form>
</main>
</body>
</html>
