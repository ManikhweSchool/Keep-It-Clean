function deselectSelectedDay(){
	
	var form = document.forms[0];
	var isDateSelected = false;
	
	for(var i =0; i < form.elements.length;i++){
		if(form.elements[i].type=="radio" )
			Console.writeln(Console.form.elements[i].value);
			
	}
}