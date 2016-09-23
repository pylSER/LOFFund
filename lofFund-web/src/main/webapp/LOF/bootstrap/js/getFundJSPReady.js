$( document ).ready(function() {
//  alert(window.screen.height); 
  
//	alert(document.body.style.height); 
	var height='';
//	height+=(window.screen.availHeight);
	height+=document.body.scrollHeight ;
	height+='px';
	
	
	document.body.style.height=height;
	
	
	 var x1=document.getElementById("block1").offsetWidth;
	  var x2=document.getElementById("block2").offsetWidth;

	    // alert(x1+x2);
	    var w=x1+x2+31;
	    w+='px';

	    document.getElementById("yt").style.width=w;
});