<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding Users</title>
</head>
<body>
	<div class="well"
		style="width: 379px; height: 466px; margin-left: 240px; margin-top: 119px; background-color: lightgray;">
		<div id="errorOnSignUp" style="text-align: center; margin-top: 9px;"></div>

		<center>
			<table style="padding-top: 65px;">
				<th><h2>Adding Users</h2></th>
				<tr>
					<td>First Name:</td>
					<td><input type="text" placeholder="firstName" id="firstName"
						name="firstName" onfocusout="firstname();" /><span
						id="firstNameVerify"></span></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" placeholder="lastName" id="lastName"
						name="lastName" onfocusout="lastname();" /> <span
						id="lastNameVerify"></span></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" placeholder="Email" id="email"
						name="email" onfocusout="validate();" /><span id="emailVerify"></span>
					</td>
				</tr>
				<tr>
					<td>password:</td>
					<td><input type="password" placeholder="password" id="password"
						name="password" onfocusout="password();" /> <span
						id="passwordVerify"></span></td>
				</tr>
			</table>
			<input type="submit" value="submit" class="form-control"
				onclick=" if(verify()){addUsers();}">
		</center>
	</div>
	<script src="registerValidate.js"></script>
	<script src="Login.js"></script>
</body>
</html>