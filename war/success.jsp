
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alarm Application</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="jquery-3.2.1.min (1).js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
.vertical_line {
	height: 3000px;
	width: 1px;
	background: #000;
}
</style>
<div id="name" style="margin-left: 1243px; margin-top: 14px;"></div>
<script>
<%String x = (String) (session.getAttribute("name"));%>
var name = "<%=x%>";

	console.log(name);
	document.getElementById("name").innerHTML = name;
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$
								.ajax({
									type : 'POST',
									url : '/ListOfTimersOfUser',
									success : function(list) {

										var arr = [];
										var a = JSON.parse(list);
										var arr = a.listOfStrings;
										arr
												.forEach(function(time) {

													var ul = document
															.getElementById("givenTime");
													var li = document
															.createElement("li");
													var lii = document
															.createElement("button");
													lii
															.setAttribute("id",
																	time);
													lii.className = "glyphicon glyphicon-trash";
													lii
															.setAttribute(
																	"style",
																	"margin-right: -63px; float: right;margin-top: -58px;");
													li.setAttribute("class",
															"list-group-item");
													li.setAttribute("class",
															"well");
													li
															.appendChild(document
																	.createTextNode(time));
													ul.appendChild(li);
													ul.appendChild(lii);

												})

									}
								});

						$('#giveTime')
								.keypress(
										function(e) {
											if (e.which == 13) {
												console
														.log("You pressed enter!");

												//alert("button pressed");
												var giveTime = $("#giveTime")
														.val();
												console.log(giveTime);
												var data = {
													"giveTime" : giveTime
												};
												var jsonobject = JSON.stringify(data);
												
														$.ajax({
															type : 'POST',
															data : jsonobject,
															url : 'Timer',
															datatype : "application/json",
															contentType : "text/plain",
															success : function(
																	result) {
																var obj = JSON
																		.parse(result);
																if (obj.key == "success") {
																	console
																			.log("data inserted success");
																	postTime();
																	$("#reset")[0]
																			.reset();
																	//$("#result1").html(result);

																} else {
																	alert("time already exists");
																	document.getElementById("errorDisplay").innerHTML = "time already exists";
																	/* var targetToDelete1 = document
																				.getElementById(giveTime).previousSibling;
																		document
																				.getElementById(giveTime).parentNode
																				.removeChild(targetToDelete1);
																		giveTime.style.display = "none";  */

																}
																//alert(obj);
																//alert(JSON.stringify(result));
															},
															error : function(
																	result) {
																alert("error");
															}

														});
											}
										});

					});
</script>
</head>
<body>

	<div class="col-md-6">
		<h2>TIME</h2>
		<div id="scrollBar"
			style="overflow-y: scroll; height: 369px; margin-right: 244px;">
			<ul id="givenTime" class="list-group"
				style="width: 296px; text-align: center; font-size: 22px; border-left: 48;"
				onclick="startTimer(event)">
			</ul>
		</div>
	</div>

	<div id="bottom" style="position: fixed; bottom: 0px;">
		<div id="addingTime" style="display: none;">
			<form id="reset">
				<input type="text" id="giveTime" name="time" onclick="this.select()"
					onKeyDown="if(event.keyCode==13);"
					style="margin-bottom: -5px; margin-left: 191px;" autofocus>
			</form>
		</div>
		<div onclick="openBox()" id="plus">
			<div id="hor">
				<hr width="446px">
			</div>
			<center>
				<h1>
					<div class="glyphicon glyphicon-plus" style="margin-left: 61px;"></div>
				</h1>
			</center>
		</div>
	</div>
	<div class="col-md-6">
		<div id="errorDisplay"></div>
		<div class="vertical_line"
			style="margin-top: -33px; margin-left: -237px; height: 651px;"></div>

	</div>
	<div class="col-md-6">

		<nav class="navbar navbar-fixed-top">
		<ul class="nav navbar-nav navbar-right">
			<!-- <li><a href="#"><span><input type="text">
</text area></span></a></li> -->
			<li><h5>
					<div class="glyphicon glyphicon-off" onclick="signOut()"
						style="margin-right: 20px; font-size: 24px;"></div>
				</h5></li>
		</ul>
		</nav>
		<div id="startTime"></div>
		<!-- <div id="display" style="font-size: 90px;margin-left: 681px;margin-top: -2827px;"></div> -->
		<!-- <div id="display"style ="margin-top: 164px;font-size: 90px;" ></div> -->
		<div id="display"
			style="font-size: 90px; margin-left: 681px; margin-top: -470px;"></div>
		<div id="stop" style="display: none" onclick="stop()">
			<span class="glyphicon glyphicon-stop"
				style="color: red; font-size =10px; font-size: 25px; margin-left: 728px;">stop</span>
			<!-- <span class="glyphicon glyphicon-stop" style="color:red;font-size=10px;font-size: 25px;">stop</span> -->
		</div>
	</div>
	<div id="result1"></div>

	<script src="alarm.js"></script>
	<script src="Login.js"></script>
</body>
</html>