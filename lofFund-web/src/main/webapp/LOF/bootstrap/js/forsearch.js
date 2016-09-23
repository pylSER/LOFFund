function hideTds() {

    document.getElementById("tabletitle").style.display='none';

    var tds = document.querySelectorAll('td');
    for (var i = 0; i < 10; i++) {
        tds[i].style.display = 'none';
    }
}
hideTds();
document.querySelector('#searchinput').onkeyup = function() {
    var tds = document.querySelectorAll('td');
    for (var i = 0; i < 10; i++) {
        tds[i].innerHTML = '';
    }
    hideTds();
    if (this.value.trim() === '') {
        return;
    }


    var t=document.getElementById("btnselect").innerText;
    t=t.trim();
    var tt=0;
    
    if(t=="全部"){
    	tt=0;
    }else if(t=="偏股"){
    	tt=2;
    }else if(t=="偏债"){
    	tt=3;
    }else if(t=="综合"){
    		tt=1;
    }
 
    $.getJSON('search.jsp',{name:(this.value.trim()),type:tt }, function (data) {
 
        if(data.length==0){
            document.getElementById("tabletitle").style.display='none';
        }else {
            document.getElementById("tabletitle").style.display=''
        };
        var tds = document.querySelectorAll('td');
        var prefix='<div class="col-md-3">';
        var postfix='</div>';
        var k=40;
        if(data.length<40){
        	k=data.length;
        }
        
       for(var i=0;i<k;i+=4){
           tds[i/4].style.display='';
//           data[i+3]=data[i+3]*100;
           tds[i/4].innerHTML=prefix+data[i]+postfix+prefix+data[i+1]+postfix+prefix+data[i+2]+postfix+prefix+data[i+3]+postfix;
       }
        
    });

};
function fn(data) {
    if(data.s.length==0){
        document.getElementById("tabletitle").style.display='none';
    }else {
        document.getElementById("tabletitle").style.display=''
    };
    var tds = document.querySelectorAll('td');
    data.s.forEach(function(item, index) {
        tds[index].style.display = '';
        tds[index].innerHTML = item;
    });
    // delete scripts
    // var s = document.querySelectorAll('script');
    // for (var i = 1, len = s.length; i < len; i++) {
    //     document.body.removeChild(s[i]);
    // }
}


document.onclick = function(e) {
    if (e.target != document.table &&e.target.id!="searchinput") {
        hideTds();
    }
}

var list = document.querySelectorAll('td');
for(var i=0;i<list.length;i++){
	list[i].onclick = function(e) {
		var wd = e.target.parentNode.innerText;
//		alert(wd.substring(0,6));
		var code=wd.substring(0,6);
		
		jumpTo(code);

	};
}

document.onmousewheel  = function(e) {
        hideTds();
}

