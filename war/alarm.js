var date =null;
var time = null;
var timer =null;
function openBox()
{
document.getElementById("addingTime").style.display="block";
}
function postTime()
{
document.getElementById("addingTime").style.display ="none";
time =document.getElementById("giveTime").value;
var ul = document.getElementById("givenTime");
var li = document.createElement("li");
var lii=document.createElement("button");
//lii.setAttribute("id","ids")
//lii.setAttribute("onclick", function() { alert("blabla"); });
li.setAttribute("class", "list-group-item");
li.setAttribute("class","well");
li.appendChild(document.createTextNode(time));
ul.appendChild(li);

ul.appendChild(lii);


}
/*function startTimer(event)
{
	var event=event;
	
document.getElementById("display").innerHTML ="";
date= new Date();
var initialtime = time;
var array =null;
array= initialtime.split(':');
console.log(array);
var hours=array[0];
var minutes=array[1];
var seconds=array[2];

//console.log(d);
date.setHours(hours);
date.setMinutes(minutes);
date.setSeconds(seconds)
clearInterval(timer);
timer=setInterval(function () 
{
document.getElementById("display").innerHTML=date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
date.setTime(date.getTime() + 1000);
}
, 1000);

}*/
function stop()
{
clearInterval(timer);
}
document.getElementById('giveTime').onkeydown = function(event){
    var e = event || window.event;
    if(e.keyCode == 13){
       //postTime();
    	time =document.getElementById("giveTime").value;
    	checkTimeFormat(time);
    }
}
function checkTimeFormat(event){
alert(event);
	var regex = new RegExp("([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])");
	if (regex.test(event)) {
	//alert("true");
	postTime();
	} else {
	alert("please enter HH:MM:SS format only ");
	}
}
/*function identify(ul){
	//var event=document.getElementById("givenTime").innerHTML;
	alert(ul);
	var target=getEventTarget(ul);
	//document.getElementById("display").innerHTML=target.innerHTML;
	alert(target);
	
}*/
/*document.getElementById("ul").onclick=function(event){
	alert("fgfgfd");
}
*/
//document.getElementById("givenTime").onclick=function(event){
function startTimer(event){
	var target = getEventTarget(event);
	document.getElementById("display").innerHTML = target.innerHTML;
	//document.getElementsByTagName("button").onclick=function(){alert("sdf");}
	alert(target.innerHTML);
	date= new Date();
	var initialtime = target.innerHTML;
	var array =null;
	array= initialtime.split(':');
	console.log(array);
	var hours=array[0];
	var minutes=array[1];
	var seconds=array[2];
	date.setHours(hours);
	date.setMinutes(minutes);
	date.setSeconds(seconds);
	clearInterval(timer);
	timer=setInterval(function () {
	document.getElementById("display").innerHTML=date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	date.setTime(date.getTime() + 1000);
	}, 1000);
	document.getElementById("stop").style.display = "block";
	};

	function getEventTarget(e) {
	e = e || window.event;
	return e.target || e.srcElement; 
	}
 
/*function startTime(event){
	if(!timer){
	startTimer(event);}
	else
		{
		clearInterval(timer);
		startTimer(event);
		}
		}*/
	  /*event.stopImmediatePropagation();
	    this.removeEventListener("click", startTimer());*/

/*function removeEvent() {
    document.getElementById("givenTime").removeEventListener('click', stop);
}*/
	    /*document.onclick = second;
	}
	function second(){
	   // alert("I'm not suppose to appear after the first click, only the second.");
		startTimer(event);
	}*/
