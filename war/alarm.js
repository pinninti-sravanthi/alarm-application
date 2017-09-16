var date =null;
var time = null;
var timer =null;
function openBox()
{
document.getElementById("addingTime").style.display="block";

}
function closeBox(){
	
	document.getElementById("addingTime").style.display ="none";
	document.getElementById("errorDisplay").style.display="none";
	
}
function postTime()
{
document.getElementById("addingTime").style.display ="none";
document.getElementById("emptyMessage").style.display="none";
giveTime =document.getElementById("giveTime").value;

var data = {"giveTime" : giveTime};
	var jsonobject = JSON.stringify(data);
			$.ajax({
				type : 'POST',
				data : jsonobject,
				url : 'Timer',
				datatype : "application/json",
				contentType : "text/plain",
				success : function(result) {
					var obj = JSON.parse(result);
					if (obj.key == "success") {
						console.log("data inserted success");
						
						var ul = document.getElementById("givenTime");
						var li = document.createElement("li");
						var lii = document.createElement("button");
						lii.setAttribute("id", time);
						lii.className="glyphicon glyphicon-trash";
						lii.setAttribute("style","margin-right: -44px; float: right;margin-top: -73px;");
						li.setAttribute("class", "list-group-item");
						li.setAttribute("class", "well");
						li.appendChild(document.createTextNode(time));
						ul.appendChild(li);
						ul.appendChild(lii);
						document.getElementById("giveTime").value=""

					} else {
						document.getElementById("addingTime").style.display ="block";
						document.getElementById("timeExists").style.display="block";
						
						setTimeout(function()
						{
						document.getElementById("timeExists").style.display="none";
						},3000);
					}
					
			 	},
				error : function(result) {
					alert("error");
				}

			});
}



function stop()
{
clearInterval(timer);
}
function addTime(event){
	 var e = event || window.event;
	    if(e.keyCode == 13){
	       //postTime();
	    	time =document.getElementById("giveTime").value;
	    	checkTimeFormat(time);
	    	//postTime();
	    }
	  	if(e.keyCode==27){
	    		document.getElementById("addingTime").style.display="none";
               }
}

function checkTimeFormat(event){
//alert(event);
	var regex = new RegExp("([0-1]?[0-9]:[0-5]?[0-9]:[0-5]?[0-9])");
	var regex1=new RegExp("[a-zA-Z |!|@|#|$|%|^|&|*|(|)|_|+ ]+");
	if (regex.test(event)) {
	//alert("true");
	postTime();
	}
	else if(regex1.test(event)){
	
	document.getElementById("errorDisplay").innerHTML="Alphabets are not allowed,enter only digits "
	}
	else
		{
		document.getElementById("errorDisplay").innerHTML="please enter HH:MM:SS format only ";
		}
}

function startTimer(event){
	var target = getEventTarget(event);
	var targetId = event.target.id;
	if(!targetId){
	document.getElementById("display").innerHTML = target.innerHTML;
	date= new Date();
	var initialtime = target.innerHTML;
	var array =null;
	array= initialtime.split(':');
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
	hrs = hrs<10?"0"+hrs:hrs;
	min = min<10?"0"+min:min;
	sec = sec<10?"0"+sec:sec;
	document.getElementById("display").innerHTML = hrs+
	":" +min+ ":" + sec;
	date.setTime(date.getTime() + 1000);
	}, 1000);
	document.getElementById("stop").style.display = "block";
	}
	else
		{
		var deltime=targetId;
	
		console.log(deltime);
		
		var xhr = new XMLHttpRequest();
		var url = "/delete";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
		var json = JSON.parse(xhr.responseText);
			console.log("success");
		
		if(json.key==="success")
		{
			document.getElementById("delete").style.display="block";
			setTimeout(function()
			{
			document.getElementById("delete").style.display="none";
			},3000);
			
		var targetToDelete = document.getElementById(targetId).previousSibling;
		document.getElementById(targetId).parentNode.removeChild(targetToDelete);
		target.style.display="none";
		}
		else{
			
		alert("fail");
		}
		
		; }
		}; 
		var data = JSON.stringify({"deltime":deltime});
		console.log(data);
		xhr.send(data);
		
		
		}
	};

	function getEventTarget(e) {
	e = e || window.event;
	return e.target || e.srcElement; 
	}
 
