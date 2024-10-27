<%--<jsp:useBean id="order" scope="request" type=""/>--%>
<%@page language="java" %>
<html>
    <head>
        <link rel="stylesheet" href="style-make.css">
        <title>Taxi</title>
    </head>
    <body>
    <div class="make-order-block">
        <h2>Make order</h2>
        <form action="made">


            <label for="distance"> Distance: </label>
            <input type="text" id="distance" name="distance"/> <br/>

            <label for="driverId"> Driver Id: </label>
            <input type="text" id="driverId" name="driverId"/> <br/>

            <label for="clientId"> Your client id: </label>
            <input type="text" id="clientId" name="clientId" value="${clientId}"/> <br/>
            <input type="submit" value="Submit">
        </form>
    </div>
    </body>
</html>
