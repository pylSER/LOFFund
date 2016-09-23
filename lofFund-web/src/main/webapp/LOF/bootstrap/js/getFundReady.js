$( document ).ready(function() {
//  alert(window.screen.height); 
  
//	alert(document.body.style.height); 
	var height='';
//	height+=(window.screen.availHeight);
	height+=document.body.scrollHeight ;
	height+='px';
	
	
	document.body.style.height=height;
});