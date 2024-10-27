<%@page language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style-make.css">
    <title>Taxi</title>
</head>
<body>
<div class="make-order-block">
    <h2>Make order</h2>
    <form action="make">

        <label for="name"> Your name: </label>
        <input type="text" id="name" name="name"/> <br/>

        <label for="surname"> Surname: </label>
        <input type="text" id="surname" name="surname"/> <br/>

        <label for="middleName"> Middle name: </label>
        <input type="text" id="middleName" name="middleName"/> <br/>

        <label for="dateOfBirth"> Date of birth: </label>
        <input type="text" id="dateOfBirth" name="dateOfBirth" placeholder="2006-03-15"/> <br/>

        <label for="phoneNumber"> Phone number: </label>
        <input type="text" id="phoneNumber" name="phoneNumber"/> <br/>

        <label for="password"> Password: </label>
        <input type="text" id="password" name="password"/> <br/>

        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
