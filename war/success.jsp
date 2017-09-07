
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
	
<script type="text/javascript">

$(document).ready(function () {
	$.ajax({
		type:'POST',
		url:'/ListOfTimersOfUser',
		success:function(list){
		
/* 			alert("success");
			console.log(list);
		var response=list;
			//var val = JSON.parse(response);
			/* for(var i=0;i<val.length;i++)
				{
				
				console.log(i);
				}
			 */
			  var arr=[];
			 /*
			 arr=response.split(',');
			alert(arr);
            alert(response.listOfStrings);	 */ 
            var a = JSON.parse(list);
             var arr=a.listOfStrings;
            arr.forEach(function(time){
            	var ul = document.getElementById("givenTime");
            	var li = document.createElement("li");
            	var lii =document.createElement("button");
            	lii.setAttribute("id",time);
            	lii.setAttribute("type","delete");
	//lii.setAttribute("click",function() { alert("blabla"); });
            	li.setAttribute("class", "list-group-item");
            	li.setAttribute("class","well");
            	li.appendChild(document.createTextNode(time));
            	ul.appendChild(li);
            	ul.appendChild(lii);
            	
            })

	}
	});
	$('#giveTime').keypress(function(e){
		if(e.which == 13) {
	        alert('You pressed enter!');
		
		//alert("button pressed");
	   var giveTime = $('#giveTime').val();
	   var data={"givenTime": giveTime};
	   var jsonobject = JSON.stringify(data);
		$.ajax({
			type:'POST',
			data : jsonobject,
			url:'Timer',
			success : function(result){
				alert(jsonobject);
				//var obj = JSON.parse(result);
				//alert(obj);
				//alert(JSON.stringify(result));
				$("#result1").html(result);
			} ,
	      error:function(result)
	      {
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
<ul id ="givenTime" class="list-group"  style="
    width: 154px;
    padding-left: 6px;
    font-size: 22px;
    border-left: 48;overflow-y: scroll;
height: 519px;" onclick="startTimer(event)">
</ul>
<!-- onclick="startTime(this.value)" -->
</div>

<!-- <div id ="bottom" style ="position: fixed;
bottom: 0px;">
<div onclick="openBox()"style="
    border-bottom: -60;
    margin-bottom: 0px;
    margin-left: 26px;
    font-size: 35px;" >+</div> -->
    
    <div id="bottom" style="position: fixed; bottom: 0px;">
<div id="addingTime" style="display: none;">
<form id="myForm">
<input type="text" id="giveTime" value="" name="time"
onclick="this.select()" onKeyDown="if(event.keyCode==13);"
style="margin-bottom: -5px; margin-left: 17px;" autofocus></form>
</div>
<div onclick="openBox()" id="plus">
<center>
<h1>
<div class="glyphicon glyphicon-plus" style="
    margin-left: 61px;
"></div>
</h1>
</center>
</div>
</div>

    

<!-- <div id="addingTime" style = "display : none;">
<input type ="text" id ="giveTime" value="" name ="time" onclick="this.select()"onKeyDown="if(event.keyCode==13);" style="
    margin-bottom: 32px; margin-left: 17px;" autofocus>
<input type="submit" onclick ="postTime()" value ="submit">
</div> -->
<!-- <div id="givenTime" onclick="startTimer()"></div> -->
<div class="col-md-6">

<nav class="navbar navbar-fixed-top">
<ul class="nav navbar-nav navbar-right">
<!-- <li><a href="#"><span><input type="text">
</text area></span></a></li> -->
<li><h5><div class="glyphicon glyphicon-off" onclick="signOut()" style="
    margin-right: 20px;
    font-size: 24px;
"></div></h5></li>
</ul>
</nav>
<div id="startTime"> </div>
<div id="display" style ="margin-top: 164px;font-size: 90px;" ></div>
<div id="stop" style="display:none" onclick="stop()">
<span class="glyphicon glyphicon-stop" style="color:red;font-size=10px;font-size: 25px;">stop</span></div>
</div>
<div id="result1"></div>
 <div id="Time">
<script src="alarm.js"></script>
<script src="Login.js"></script>
</body>
</html>