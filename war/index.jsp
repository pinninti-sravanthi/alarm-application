
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alarm Application</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="jquery-3.2.1.min (1).js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="Login.js"></script>
</head>
<body background="images/bk4.jpg">
	<div class="well"
		style="width: 379px; height: 466px; margin-left: 471px; margin-top: 90px;">
		<h1
			style="text-align: center; padding-top: 1px; color: cornflowerblue;">Welcome!</h1>
		<!-- 	<div class="Button" style="text-align: center"> -->
		<center>
			<button onclick="showSignUp()"
				style="color: brown; width: 82px; margin-left: -87px;"
				class="form-control" autofocus>SignUp</button>
			<button onclick="showSignIn()"
				style="color: brown; width: 82px; margin-left: 95px; margin-top: -35px;"
				class="form-control">SignIn</button>
		</center>
		<!-- </div> -->
		<script>
			function showSignUp() {
				document.getElementById("signUp").style.display = "block";
				document.getElementById("signIn").style.display = "none";
			}
			function showSignIn() {
				document.getElementById("signIn").style.display = "block";
				document.getElementById("signUp").style.display = "none";
			}
			

			var  valid , valid1 , valid2, valid3 ;
			var passwordValid , emailValid;
			function firstname() {
				var firstname = document.getElementById('firstName').value;
				var regex = new RegExp("[a-zA-Z]+")

				if (firstname.length > 1 && firstname.match(regex)) {
					var icon = document.getElementById("as");
					icon.className = "glyphicon glyphicon-ok";
					icon.setAttribute("style", "color:green");
					valid = true;
				} else {
					var icon = document.getElementById("as");
					icon.className = "glyphicon glyphicon-remove";
					icon.setAttribute("style", "color:red");
					document.getElementById("errorOnSignUp").innerHTML = "Give appropriate firstname"
					valid = false;

				}

			}
			function lastname() {
				var lastname = document.getElementById('lastName').value;
				var regex = new RegExp("[a-zA-Z]+")
				if (lastname.length > 1 && lastname.match(regex)) {
					var icon = document.getElementById("as1");
					icon.className = "glyphicon glyphicon-ok";
					icon.setAttribute("style", "color:green");
					valid1 = true;
					/* var span=document.getElementById('as');
					var icon="glyphicon glyphicon-tick"
					span.append(icon);
					 */} else {
					var icon = document.getElementById("as1");
					icon.className = "glyphicon glyphicon-remove";
					icon.setAttribute("style", "color:red");
					document.getElementById("errorOnSignUp").innerHTML = "Give appropriate lastname"
					valid1 = false;

				}

			}

			function validate() {
				var email = document.getElementById("email").value;

				var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				//var pass = document.getElementById('password').value;

				if (email.match(mailformat)) {
					var icon = document.getElementById("as2");
					icon.className = "glyphicon glyphicon-ok";
					icon.setAttribute("style", "color:green");

					valid2 = true;
				} else {
					var icon = document.getElementById("as2");
					icon.className = "glyphicon glyphicon-remove";
					icon.setAttribute("style", "color:red");
					document.getElementById("errorOnSignUp").innerHTML = "Give appropriate mail"
					valid2 = false;

				}
			}
			function password() {

				var password = document.getElementById('password').value;
				var regex = new RegExp("[a-zA-Z|0-9]+");

				if (password.length > 5 && password.match(regex)) {
					var icon = document.getElementById("as3");
					icon.className = "glyphicon glyphicon-ok";
					icon.setAttribute("style", "color:green");

					valid3 = true;
					/* var span=document.getElementById('as');
					var icon="glyphicon glyphicon-tick"
					span.append(icon);
					 */} else {
					var icon = document.getElementById("as3");
					icon.className = "glyphicon glyphicon-remove";
					icon.setAttribute("style", "color:red");
					document.getElementById("errorOnSignUp").innerHTML = "password should be minimum of 6 characters"
					valid3 = false;
				}

			}

			
			
			function verify() {
				if (valid == true && valid1 == true && valid2 == true
						&& valid3 == true) {
					return true;
				} else {
					document.getElementById("errorOnSignUp").innerHTML = "please enter the credentials"
					return false;
				}
			}
			
			function Validate() {
				var email = document.getElementById("emailId").value;

				var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				//var pass = document.getElementById('password').value;

				if (email.match(mailformat)) {
					/* var icon = document.getElementById("as2");
					icon.className = "glyphicon glyphicon-ok";
					icon.setAttribute("style","color:green");
					 */
					emailValid = true;
				} else {
					/* var icon = document.getElementById("as2");
					/icon.className = "glyphicon glyphicon-remove";
					icon.setAttribute("style","color:red"); */
					document.getElementById("errorOnSignIn").innerHTML = "Give appropriate mail"
					emailValid = false;

				}
			}
			function Password() {

				var password = document.getElementById('Password').value;
				var regex = new RegExp("[a-zA-Z|0-9]+");

				if (password.length > 5 && password.match(regex)) {
					/* var icon = document.getElementById("as3");
					icon.className = "glyphicon glyphicon-ok";
					icon.setAttribute("style","color:green"); */

					passwordValid = true;
					/* var span=document.getElementById('as');
					var icon="glyphicon glyphicon-tick"
					span.append(icon);
					 */} else {
					/* var icon = document.getElementById("as3");
					icon.className = "glyphicon glyphicon-remove";
					icon.setAttribute("style","color:red"); */
					document.getElementById("errorOnSignIn").innerHTML = "password should be minimum of 6 characters"
					passwordValid = false;
				}
			}
				function Verify() {
					if (emailValid == true && passwordValid == true) {
						return true;
					} else {
						document.getElementById("errorOnSignIn").innerHTML = "please enter the Login credentials"
						return false;
					}
				}

		</script>

		<div id='signUp'>
			<div id="errorOnSignUp" style="text-align: center; margin-top: 9px;"></div>


			<center>
				<div class="inner-addon left-addon">
					<i class="glyphicon glyphicon-user"></i><input type="text"
						name="firstName" id="firstName" placeholder="FirstName" required
						class="form-control" style="margin-top: 14px;"
						onfocusout="firstname();"> <span id="as"></span>
				</div>
				<br>
				<div class="inner-addon left-addon">
					<i class="glyphicon glyphicon-user"></i><input type="text"
						name="lastName" id="lastName" placeholder="LastName" required
						class="form-control" onfocusout="lastname();"> <span
						id="as1"></span>
				</div>
				<br>
				<div class="inner-addon left-addon">
					<i class="glyphicon glyphicon-envelope"></i><input type="email"
						name="email" id="email" placeholder="email" required
						class="form-control" onfocusout="validate();"> <span
						id="as2"></span>
				</div>
				<br>
				<div class="inner-addon left-addon">
					<i class="glyphicon glyphicon-lock"></i><input type="password"
						name="password" id="password" placeholder="password" required
						class="form-control" onfocusout="password();"> <span
						id="as3"></span>

				</div>
				<br>
				<div class="inner-addon left-addon">
					<input type="submit" value="signup" class="form-control"
						onclick="if(verify()){Register();}"
						style="width: 91px; padding-left: 12px;">
				</div>
			</center>

		</div>
		<center>
		
			<div id='signIn' style="display: none;">
			<div id="errorOnSignIn" style="text-align: center;margin-top: 9px;"></div>
				
				<br>
				<div class="inner-addon left-addon">
					<i class="glyphicon glyphicon-envelope"></i> <input type="email"
						class="form-control" id="emailId" placeholder="Email"
						onfocusout="Validate();" />
				</div>
				<br>

				<div class="inner-addon left-addon">
					<i class="glyphicon glyphicon-lock"></i><input type="password"
						name="password" id="Password" class="form-control"
						placeholder="Password" onfocusout="Password();" required>
				</div>

				<br>
				<div class="inner-addon left-addon">
					<input type="submit" value="signin" class="form-control"
						onclick="if(Verify()){Login();}"
						style="width: 91px; padding-left: 12px;" required>
				</div>
			</div>
		</center>
	</div>

</body>
</html>
<style>
.inner-addon {
	position: relative;
	width: 200px;
}

.inner-addon .glyphicon {
	position: absolute;
	padding: 10px;
	pointer-events: none;
}

/* align icon */
.left-addon .glyphicon {
	left: 0px;
}

.right-addon .glyphicon {
	right: 0px;
}

/* add padding */
.left-addon input {
	padding-left: 30px;
}

.right-addon input {
	padding-right: 30px;
}

#as {
	margin-left: 202px;
	margin-top: -1px;
	float: right;
}

#as1 {
	margin-left: 202px;
	margin-top: -1px;
	float: right;
}

#as2 {
	margin-left: 202px;
	margin-top: -1px;
	float: right;
}

#as3 {
	margin-left: 202px;
	margin-top: -1px;
	float: right;
}
</style>
