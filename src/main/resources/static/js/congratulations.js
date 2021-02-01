// Holds an identifier that will be returned by the setInterval method.
var id;
var message = "Congratulations!!!!!!!!!!!!!!";

function displayMessage(){
	
	document.getElementById("congratulations").innerHTML = message;
	if(message=="")
		message = "Congratulations!!!!!!!!!!!!!!";
	else
		message = "";
}

function displayFlashingMessage(){
	/* setInterval allows execution of a statement 
	 * repeatedly after an interval.*/
	id = window.setInterval(displayMessage, 500);
}

