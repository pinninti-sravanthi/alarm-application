function showSignUp() {
			document.getElementById("signUp").style.display = "block";
			document.getElementById("signIn").style.display = "none";
		}
		function showSignIn() {
			document.getElementById("signIn").style.display = "block";
			document.getElementById("signUp").style.display = "none";
		}

		var valid, valid1, valid2, valid3;
		var passwordValid, emailValid;
		function firstname() {
			var firstname = document.getElementById('firstName').value;
			var regex = new RegExp("[a-zA-Z]+")

			if (firstname.length > 1 && firstname.match(regex)) {
				var icon = document.getElementById("firstNameVerify");
				icon.className = "glyphicon glyphicon-ok";
				icon.setAttribute("style", "color:green");
				//document.getElementById("errorOnSignUp").style.display="none";
				valid = true;
			} else {
				var icon = document.getElementById("firstNameVerify");
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
				var icon = document.getElementById("lastNameVerify");
				icon.className = "glyphicon glyphicon-ok";
				icon.setAttribute("style", "color:green");
				valid1 = true;
				} else {
				var icon = document.getElementById("lastNameVerify");
				icon.className = "glyphicon glyphicon-remove";
				icon.setAttribute("style", "color:red");
				document.getElementById("errorOnSignUp").innerHTML = "Give appropriate lastname"
				valid1 = false;

			}

		}

		function validate() {
			var email = document.getElementById("email").value;

			var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			
			if (email.match(mailformat)) {
				var icon = document.getElementById("emailVerify");
				icon.className = "glyphicon glyphicon-ok";
				icon.setAttribute("style", "color:green");

				valid2 = true;
			} else {
				var icon = document.getElementById("emailVerify");
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
				var icon = document.getElementById("passwordVerify");
				icon.className = "glyphicon glyphicon-ok";
				icon.setAttribute("style", "color:green");

				valid3 = true;
				} else {
				var icon = document.getElementById("passwordVerify");
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
			

			if (email.match(mailformat)) {
				
				emailValid = true;
			} else {
				document.getElementById("errorOnSignIn").innerHTML = "Give appropriate mail"
				emailValid = false;

			}
		}
		function Password() {

			var password = document.getElementById('Password').value;
			var regex = new RegExp("[a-zA-Z|0-9]+");

			if (password.length > 5 && password.match(regex)) {

				passwordValid = true;
				} else {
				
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
		function firstNameDisplay(){
			document.getElementById("firstNameVerify").style.display="none";	
			
			
		}
		function lastNameDisplay(){
			document.getElementById("lastNameVerify").style.display="none";
			
		}
		function emailDisplay(){
			document.getElementById("emailVerify").style.display="none";
			
		}
		function passwordDisplay(){
			document.getElementById("passwordVerify").style.display="none";
			
		}