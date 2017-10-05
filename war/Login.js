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

			if (json.key1 == "fail1" ) {
				console.log("key");
				document.getElementById("errorOnSignIn").innerHTML = "enter the registered details"
					//window.location.href="/adduser";
			} 
			else if( json.key3 == "fail2"){
				document.getElementById("errorOnSignIn").innerHTML = "enter the  added registered details"
			}
		 if(json.key4 == "success2" && json.key1 == "fail1" ){
			 document.getElementById("errorOnSignIn").style.diplay="none" ;
			 						window.location.href="/afterlogin";
			}
			
			if(json.key2 == "success1"){
				window.location.href="/afterlogin";
			}
				
			/*else {
				document.getElementById("errorOnSignIn").innerHTML = "error"

			};*/
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
				localStorage.removeItem("pauseTime");
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
function addUsers(){
	var firstName=document.getElementById("firstName").value;
	var useremailId=document.getElementById("email").value;
	var pass=document.getElementById("password").value;
	var lastName=document.getElementById("lastName").value;
	var xhr=new XMLHttpRequest();
	var url ="/addUsers";
	xhr.open("POST",url,true);
	xhr.setRequestHeader("Content-type","application/json");
	xhr.onreadystatechange=function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var json=JSON.parse(xhr.responseText);
			if (json.key == "success") {
				document.getElementById("errorOnSignUp").innerHTML = "register success"
					//window.location.href="/sendEmail"
				
			} else {
				document.getElementById("errorOnSignUp").innerHTML = "unable to register"
				
			};
		}
	};
	var data=JSON.stringify({
		"firstName":firstName,
		"useremailId":useremailId,
		"pass":pass,
		"lastName":lastName	
	});
	console.log(firstName);
	xhr.send(data);
}
