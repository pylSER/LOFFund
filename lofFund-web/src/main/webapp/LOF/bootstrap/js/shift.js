function changePageForAll(pnum){
	$(function () {
	    $.getJSON('sendData2showall.jsp',{num:pnum},function (data){
			for(var i = 0;i<data.length;i++){
				if(i==7){
					break;
				}

				var code = data[i][1];
				var method = "jumpTo("+data[i][1]+")";
				document.getElementById("name"+i).parentNode.parentNode.onclick = new Function(method);
				// document.getElementById("name"+i).parentNode.parentNode.attachEvent("onclick",jumpTo(data[i][1]));
//				 alert(document.getElementById("name"+i).parentNode.parentNode.onclick);

				document.getElementById("name"+i).innerHTML=data[i][0];
				document.getElementById("code"+i).innerHTML=data[i][1];
				document.getElementById("yesterday"+i).innerHTML=data[i][2];
				document.getElementById("rate"+i).innerHTML=data[i][3];
				document.getElementById("buyone"+i).innerHTML=data[i][4];
				document.getElementById("predict"+i).innerHTML=data[i][5];

				if(data[i][6]=="1"){
					// if(document.getElementById("name"+i).nextElementSibling.className=="profiticon"){
						document.getElementById("name"+i).nextElementSibling.style.display = "inline-block";
					// }else{
					// 	var icon = document.createElement("span");
					// 	icon.className="profiticon";
					// 	icon.style="display: inline-block;";
					// 	var txt = document.createElement("div");
					// 	txt.style="margin-top: 2px";
					// 	txt.innerHTML="可套利";
					// 	icon.appendChild(txt);
					// 	document.insertBefore(icon,document.getElementById("code"+i));
					// }

					document.getElementById("pic"+i).firstElementChild.src="bootstrap/redarrow.svg";
				}else{
					// if(document.getElementById("name"+i).nextElementSibling.className=="profiticon"){
						document.getElementById("name"+i).nextElementSibling.style.display = "none";
					// }
					document.getElementById("pic"+i).firstElementChild.src="bootstrap/greenarrow.svg";
				}
				document.getElementById("loading").style.display="none";
			}
	    });
	});

	

}


function changePageForSearch(pnum,pname,ptype){
	$(function () {
	    $.getJSON('sendData2search.jsp',{num:pnum,name:pname,type:ptype},function (data){
			for(var i = 0;i<data.length;i++){
				if(i==7){
					break;
				}

				var code = data[i][1];
				var method = "jumpTo("+data[i][1]+")";
				document.getElementById("name"+i).parentNode.parentNode.onclick = new Function(method);

				document.getElementById("name"+i).innerHTML=data[i][0];
				document.getElementById("code"+i).innerHTML=data[i][1];
				document.getElementById("yesterday"+i).innerHTML=data[i][2];
				document.getElementById("rate"+i).innerHTML=data[i][3];
				document.getElementById("buyone"+i).innerHTML=data[i][4];
				document.getElementById("predict"+i).innerHTML=data[i][5];

				if(data[i][6]=="1"){
					document.getElementById("name"+i).nextElementSibling.style.display = "inline-block";
					document.getElementById("pic"+i).firstElementChild.src="bootstrap/redarrow.svg";
				}else{
					document.getElementById("name"+i).nextElementSibling.style.display = "none";
					document.getElementById("pic"+i).firstElementChild.src="bootstrap/greenarrow.svg";
				}
				document.getElementById("loading").style.display="none";
			}
	    });
	});
}

function goaway(){
	document.getElementById("loading").style.display="none";
}

