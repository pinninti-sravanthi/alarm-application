<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alarm Application</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="jquery-3.2.1.min (1).js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="addUsers.jsp"></script>
<style>

.vertical_line {
/* height: 3000px; */
width: 1px;
background: #000;
}
</style>
<div id="name" style="margin-left: 1265px; margin-top: 14px;"></div>
<!-- <button type="button" style="margin-left: 1154px;margin-top: -19px;" onclick="'alert(hii)'">AddUsers</button> -->

 
<!-- <div>
 <select  style="margin-left: 1230px; margin-top: 14px;">
<div id="name" style="margin-left: 1243px; margin-top: 14px;"></div>
  <option value="name"id="name"></option>
</select> 
</div> -->
<script>
<%String x = (String) (session.getAttribute("name"));%>
 name = "<%=x%>";
	console.log(name);
	document.getElementById("name").innerHTML = name;
	
</script>


</head>
 <body onfocusout="closeBox()" background="images/successbg.jpg" >


	<div class="col-md-6">
		<h2>TIME</h2>
		<div id="scrollBar" style="overflow-y: auto; height: 498px; margin-right: 217px;">
		<h4><div id="emptyMessage" style="display:block; text-align:center"></div></h4>
			<ul id="givenTime" class="list-group"
				style="width: 367px; text-align: center; font-size: 22px; border-left: 48;"
				onclick="startTimer(event)">
			</ul>
		</div>
	</div>

	<div id="bottom" style="position: fixed; bottom: 0px;">
		<div id="addingTime" style="display: none;">
		<p id="timeExists" style="display:none;text-align:center">Time already Exists</p>
		<p id="delete" style="display:none;text-align:center;">Deleted Successfully</p>
		 <div id="errorDisplay" style="text-align:center"></div>
	
				<input type="text"  id="giveTime" name="time" 
				onkeydown="addTime(event);"
					style="width: 474px;margin-left: 1px;text-align: center;" autofocus>
			
		</div>
		<div onclick="openBox()" id="plus">
			<div id="hor">
				<h3 style="background-color:black; padding: 1% 475px 0% 0;">
			</div>
			<center>
			
				<h1>
					<div class="glyphicon glyphicon-plus" style="text-align:center"></div>
				</h1>
			</center>
		</div>
	</div>
	 <div class="col-md-6">
		<div class="vertical_line" style="margin-top: -43px; margin-left: -224px; height:100vh; "></div> 
	</div> 
	<div class="col-md-6">

		<nav class="navbar navbar-fixed-top">
		<ul class="nav navbar-nav navbar-right">
			
			<li><h5><div class="glyphicon glyphicon-off" onclick="signOut()" style="margin-right: 20px; font-size: 24px;"></div></h5></li>
			<!-- <li><button type="button" style="margin-right: 30px; " onclick="alert('Hello world!')">Click Me!</button></li> -->
		</ul>
		<!-- <form method="post"action="/addUsers.jsp"onsubmit="return function()"> -->
		<a href="addUsers.jsp"><button type="submit" style="margin-left: 1263px;margin-top: 10px;">Add Users!</button></a>
		<!-- </form> -->
		</nav>
		<div id="startTime"></div>
		<div id="display" style="font-size: 90px; margin-left: 681px; margin-top: -470px;">00:00:00</div>
		<div id="stop" style="display: none" onclick="stop()">
		<span class="glyphicon glyphicon-stop" style="color: red; font-size =10px; font-size: 25px; margin-left: 728px;">stop</span></div>
		<span id="pause" onclick="pauseTime()"  style="display:none;color: green; font-size =10px; font-size: 25px; margin-left: 730px;">pause</span> 
		 <!--  <div id="resume" onclick="resume()"> --><!-- class="glyphicon glyphicon-pause" -->
		<!-- <span id="resume"class="glyphicon glyphicon-play-circle" style=" display:none;color:blue; font-size =10px; font-size: 25px; margin-left: 730px;">resume</span></div>  -->
	</div>
	
	<div id="result1"></div>

	<script src="alarm.js"></script>
	<script src="Login.js"></script>
	<script>
	runTimer=localStorage.getItem("pauseTime")
	if(runTimer!=null){
	document.getElementById("display").innerHTML = runTimer;
	document.getElementById("pause").style.display="block";
	document.getElementById("stop").style.display="block";
	date = new Date();
	var initialtime = runTimer;
	var array = null;
	array = initialtime.split(':');
	console.log(array);
	var hours = array[0];
	var minutes = array[1];
	var seconds = array[2];
	date.setHours(hours);
	date.setMinutes(minutes);
	date.setSeconds(seconds);
	clearInterval(timer);
	timer = setInterval(function() {
		var hrs = date.getHours();
		var min = date.getMinutes();
		var sec = date.getSeconds();
		hrs = hrs < 10 ? "0" + hrs : hrs;
		min = min < 10 ? "0" + min : min;
		sec = sec < 10 ? "0" + sec : sec;
		document.getElementById("display").innerHTML = hrs + ":" + min
				+ ":" + sec;
		date.setTime(date.getTime() + 1000);
	}, 1000);
	}
	else{
		document.getElementById("display").innerHTML="00:00:00";
	}
	</script>
	<script type="text/javascript">
	
	$(document).ready(function() {
						$.ajax({
									type : 'POST',
									url : '/ListOfTimersOfUser',
									success : function(list) {
										var arr = [];
										var a = JSON.parse(list);
										var arr = a.listOfStrings;
										if(arr.length>0){
										arr.forEach(function(time) {
											document.getElementById("emptyMessage").style.display="none";
													var ul = document.getElementById("givenTime");
													var li = document.createElement("li");
													var lii = document.createElement("button");
													lii.setAttribute("id",time);
													lii.className = "glyphicon glyphicon-trash";
													lii.setAttribute("style","margin-right: -44px; float: right;margin-top: -73px;");
													li.setAttribute("class","list-group-item");
													li.setAttribute("class","well");
													li.appendChild(document.createTextNode(time));
													ul.appendChild(li);
													ul.appendChild(lii);
													document.getElementById("giveTime").value=""

												})
										}
										else
											{
											
											document.getElementById("emptyMessage").innerHTML="No Timers are available";
											
											
											}
									}
								});
 
					});
	</script>
	
</body>
<%
response.setHeader("Cache-Control", "no-cache");

//Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store");

//Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0);

//Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache");
//HTTP 1.0 backward enter code here

String userName = (String) session.getAttribute("email");

if (null == userName) {
//request.setAttribute("Error", "Session has ended. Please logenter code herein.");
RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
rd.forward(request, response);
}
%>
</html>