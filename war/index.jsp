
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="css/bootstrap.min.css">
<script
	src="jquery-3.2.1.min (1).js"></script>
	<script
	src="js/bootstrap.min.js"></script>
<script src="Login.js"></script>
</head>
<body background="images/bk4.jpg">
<div class="well" style="
    width: 379px;
    height: 435px;
    margin-left: 471px;
    margin-top: 90px;
">
	<h1
		style="text-align: center; padding-top: 30px; color: cornflowerblue;">Welcome!</h1>
<!-- 	<div class="Button" style="text-align: center"> -->
	<center>	<button onclick="showSignUp()" style="color: brown;width: 82px;margin-left: -87px;" class="form-control">SignUp</button>
		<button onclick="showSignIn()" style="color: brown;width: 82px;margin-left: 95px;margin-top: -35px;"  class="form-control">SignIn</button></center>
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
	</script>



	<div id='signUp'>
	<center>
<div class="inner-addon left-addon">
<i class="glyphicon glyphicon-user"></i><input type="text" name="firstName" id="firstName" class="form-control" style="
    margin-top: 14px;
"></div>
<br>				
<div class="inner-addon left-addon">
<i class="glyphicon glyphicon-user"></i><input type="text" name="lastName" id="lastName" class="form-control"></div>
<br>			
<div class="inner-addon left-addon">
<i class="glyphicon glyphicon-user"></i><input type="email" name="email" id="email" class="form-control"></div>
	<br>			
	<div class="inner-addon left-addon">
<i class="glyphicon glyphicon-lock"></i><input type="password" name="password" id="password" class="form-control"></div><br>
<div class="inner-addon left-addon"><input type="submit" value="signin" class="form-control" onclick="signupcall()" style="
    width: 91px;
    padding-left: 12px;s
">
	</div>
	</div>
<center>
	 <div id='signIn' style="display: none;"> 
	<br>	
<div class="inner-addon left-addon"><i class="glyphicon glyphicon-user"></i> <input type="email" class="form-control" id="email1"/></div>
<br>

<div class="inner-addon left-addon">
<i class="glyphicon glyphicon-lock"></i><input type="password" name="password" id="password1" class="form-control"></div>
<br>
<div class="inner-addon left-addon"><input type="submit" value="signin" class="form-control" onclick="makeLoginAjax()" style="
    width: 91px;
    padding-left: 12px;
">
</div>
</div>
</div>
</center>
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
</style>