<%@page language="java" %>
<%--<jsp:useBean id="res" scope="request" type="java.lang.String">--%>
<html>
<head>
    <link rel="stylesheet" href="style-make.css">
    <title>Order</title>
</head>
<body>
<div class="make-order-block">
    <h1>Order was successfully made!</h1><br/>
    <p>Client: ${client}</p><br/>
    <p>Driver: ${driver}</p><br>
    <p>Car is already on the way...</p>
</div>
</body>
</html>
<%--</jsp:useBean>--%>