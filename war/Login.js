function Login()
 {
		 var email = document.getElementById("emailId").value;
		 var password = document.getElementById("Password").value;
		 console.log(email);
		 var xhr = new XMLHttpRequest();
		 var url = "/LoginServlet";
		 xhr.open("POST", url, true);
		 xhr.setRequestHeader("Content-type", "application/json");
		 xhr.onreadystatechange = function () {
		 if (xhr.readyState == 4 && xhr.status == 200) {
		 var json = JSON.parse(xhr.responseText);
		 
		 //console.log(json.email);
		 
		 //alert("success");
		// alert(data);
		 if(json.key=="success"){
			 alert("enter the registered details");
			  //json(name);
			
		 }
		 else
		 {
		// alert("success");
		 //alert(json.name);
			//String name=json.name;
		 window.location.href="/afterlogin";
		
		 }
		
		 //document.getElementById("myform").reset();
		 
		 ; }
		 }; 
		 var data = JSON.stringify({"email": email, "password": password });
		 console.log(email);
		 console.log(password);
		 xhr.send(data);	 
 }
 function signOut(){
	// var email = document.getElementById("email").value;
	// console.log(email);
	 //var password = document.getElementById("password").value;
	// console.log(name);
	 var xhr = new XMLHttpRequest();
	 //var email = "akhil";
	 var url = "/Signout";
	 xhr.open("POST", url, true);
	 xhr.setRequestHeader("Content-type", "application/json");
	 xhr.onreadystatechange = function () {
	 if (xhr.readyState == 4 && xhr.status == 200) {
		// alert("success");
	 
	    window.location.href="/aftersignout";
	
	 ; }
	 }; 
	 var data = JSON.stringify({"email": "email"});
	 //console.log(name);
	 xhr.send(data);
	 
	 
	 
	 
 }
 function Register()
 {
	 var firstName= document.getElementById("firstName").value;
	 var lastName= document.getElementById("lastName").value;
	 var Email= document.getElementById("email").value;
     var password = document.getElementById("password").value;
	 console.log(Email);
	 var xhr = new XMLHttpRequest();
	 var url = "/AuthenticationServlet";
	 xhr.open("POST", url, true);
	 xhr.setRequestHeader("Content-type", "application/json");
	 xhr.onreadystatechange = function () {
	 if (xhr.readyState == 4 && xhr.status == 200) {
	 var json = JSON.parse(xhr.responseText);
	 //console.log(json.email);
	 //document.getElementById("demo").innerHTML = this.responseText;
	 if(json.key=="success"){
		 //alert("launch other page");
		 window.location.href="/register";
	 }
	 else
		 {
		 alert("unable to register,email already exists ,please try with different emailId");
		 }
	 //window.location.href="/afterlogin";
	 //document.getElementById("myform").reset();
	 ; }
	 }; 
	 var data = JSON.stringify({"firstname": firstName,"lastname":lastName,"Email":Email, "password": password });
	 //console.log(name);
	 xhr.send(data);
	 
	 
	 
 }
 
	