function Login() {
	var email = document.getElementById("emailId").value;
	var password = document.getElementById("Password").value;
	console.log(email);
	var xhr = new XMLHttpRequest();
	var url = "/LoginServlet";
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var json = JSON.parse(xhr.responseText);

			if (json.key == "success") {
				document.getElementById("errorOnSignIn").innerHTML = "enter the registered details"

			} else {

				window.location.href = "/afterlogin";

			};
		}
	};
	var data = JSON.stringify({
		"email" : email,
		"password" : password
	});
	console.log(email);
	console.log(password);
	xhr.send(data);
}
function signOut() {
	var xhr = new XMLHttpRequest();
	var url = "/Signout";
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var json = JSON.parse(xhr.responseText);
			if (json.key == "success") {
				console.log("response");
				window.location.href = "/aftersigout";

			};
		}
	};
	xhr.send("");

}
function Register() {
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var Email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	console.log(Email);
	var xhr = new XMLHttpRequest();
	var url = "/AuthenticationServlet";
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var json = JSON.parse(xhr.responseText);
			
			if (json.key == "success") {
			
				window.location.href = "/register";
			} else {
				document.getElementById("errorOnSignUp").innerHTML = "email already exists ,please try with different emailId"
				
			};
		}
	};
	var data = JSON.stringify({
		"firstname" : firstName,
		"lastname" : lastName,
		"Email" : Email,
		"password" : password
	});
	
	xhr.send(data);

}
