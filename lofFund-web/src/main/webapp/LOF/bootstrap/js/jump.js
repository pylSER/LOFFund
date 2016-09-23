/**
 * Created by peiyulin on 16/8/28.
 */

var spagenum;
function closerow() {
    document.getElementById("yulunrow").style.display="none";
}

function yuqing() {
    document.getElementById("yuqing").style.display="block";
}

function yuqing2() {
    document.getElementById("badyuqing").style.display="block";
}

function closeit() {
    document.getElementById("yuqing").style.display="none";
}

function closeit2() {
    document.getElementById("badyuqing").style.display="none";
}



function gosearch() {
	var type = document.getElementById("btnselect").innerText;

	type = type.substring(0, 2);
	type = type.trim();

	var fundinfo = document.getElementById("searchinput").value;

	fundinfo = fundinfo.trim();

	if (fundinfo == '') {
		window.location.href = 'showall.jsp';
		return;
	}

	var tt = 0;

	if (type == "全部") {
		tt = 0;
	} else if (type == "偏股") {
		tt = 2;
	} else if (type == "偏债") {
		tt = 3;
	} else if (type == "综合") {
		tt = 1;
	}

	var link = 'searchPage.jsp?';
	link += 'type=';
	link += tt;

	link += '&name=';
	link += fundinfo;
	window.location.href = link;

}

function jumpTo(code) {
	var link = 'fund.jsp?';
	link += 'code=';
	link += code;

	window.location.href = link;
}

function gotoIndex() {
	window.location.href = 'index.jsp';
}

function gotoFund() {
	window.location.href = 'showall.jsp';
}

function gotoDescription() {
	window.location.href = 'algo.html';
}

function showmore() {
	window.location.href = 'showmore.jsp';
}

function selectType(typeNum) {
	if (typeNum == 0) {
		document.getElementById("btnselect").innerHTML = "全部 \<span class=\"caret\"></span>";
	}
	if (typeNum == 1) {
		document.getElementById("btnselect").innerHTML = "综合 \<span class=\"caret\"></span>";
	}
	if (typeNum == 2) {
		document.getElementById("btnselect").innerHTML = "偏股 \<span class=\"caret\"></span>";
	}
	if (typeNum == 3) {
		document.getElementById("btnselect").innerHTML = "偏债 \<span class=\"caret\"></span>";
	}
}

function smallgosearch(mode,pagenum) {
	
	var fundinfo;
	if (mode == 0) {
		fundinfo = document.getElementById("smallsearch").value;
		fundinfo = fundinfo.trim();
		document.getElementById("hiddeninfo").innerText = fundinfo;
	} else {
		fundinfo = document.getElementById("hiddeninfo").innerText;
		fundinfo = fundinfo.trim();
	}

	var tt = 0;

	if (fundinfo == "") {
		pagenum--;
		var length=0;
		$.getJSON('simplegetall.jsp',{}, function (data) {
			length = data.length / 2;
			var id = 'sbar';
			var j = pagenum*8*2;
			
			for (var i = 1; i < 17; i += 2) {// code
				var cid = id + i;

				if (typeof (data[j]) == "undefined") {
					document.getElementById(cid).innerText = "";
					document.getElementById(cid).onclick = function(){}; 

				} else {
					document.getElementById(cid).innerText = data[j];
					var cc="jumpTo("+data[j]+")";
					document.getElementById(cid).parentNode.onclick =new Function(cc);
				}
				j += 2;
			}
			j = pagenum*8*2+1;
			for (var i = 2; i < 17; i += 2) {// name
				var cid = id + i;
				if (typeof (data[j]) == "undefined") {
					document.getElementById(cid).innerText = "";
					document.getElementById(cid).onclick = function(){};
				} else {
					document.getElementById(cid).innerText = data[j];
				}
				j += 2;
			}
			
			
			if(mode==0){
				$(function() {
					$("#demo_pag1").bs_pagination({
						mainWrapperClass : "",
						navListContainerClass : "",
						containerClass : "",
						showRowsPerPage : false,
						showGoToPage : false,
						showRowsInfo : false,
						rowsPerPage : 7,
						visiblePageLinks: 3,
						totalPages : Math.ceil(length / 8.0),
						onChangePage : function(event, data) { // returns page_num
																// and
							// rows_per_page after a
							// link has clicked
							smallgosearch(1,data.currentPage);
						}
					});

				});
			}
			
			
			
			
			
			
	    });

		
		
		
		
		
		return;
	}
	
	if (mode == 0) {
		var length = 0;

		$.getJSON('search.jsp', {name : fundinfo,type : tt}, function(data) {
			length = data.length / 4;
			var id = 'sbar';
			var j = 0;
			for (var i = 1; i < 17; i += 2) {// code
				var cid = id + i;

				if (typeof (data[j]) == "undefined") {
					document.getElementById(cid).innerText = "";
					document.getElementById(cid).onclick = function(){};
				} else {
					document.getElementById(cid).innerText = data[j];
					var method = "jumpTo("+data[j]+")";
//					alert(data[j]);
					document.getElementById(cid).parentNode.onclick = new Function(method);
				}
				j += 4;
			}
			j = 1;
			for (var i = 2; i < 17; i += 2) {// name
				var cid = id + i;
				if (typeof (data[j]) == "undefined") {
					document.getElementById(cid).innerText = "";
					document.getElementById(cid).onclick = function(){};
				} else {
					document.getElementById(cid).innerText = data[j];
				}
				j += 4;
			}

			$(function() {
				$("#demo_pag1").bs_pagination({
					mainWrapperClass : "",
					navListContainerClass : "",
					containerClass : "",
					showRowsPerPage : false,
					showGoToPage : false,
					showRowsInfo : false,
					rowsPerPage : 7,
					visiblePageLinks: 3,
					totalPages : Math.ceil(length / 8.0),
					onChangePage : function(event, data) { // returns page_num
															// and
						// rows_per_page after a
						// link has clicked
						smallgosearch(1,data.currentPage);
					}
				});

			});
		});

	}else{
		
		$.getJSON('search.jsp',{name : fundinfo,type : tt}, function (data) {
			pagenum--;
			var id = 'sbar';
			var j = pagenum*8*4;
			
			for (var i = 1; i < 17; i += 2) {// code
				var cid = id + i;

				if (typeof (data[j]) == "undefined") {
					document.getElementById(cid).innerText = "";
					document.getElementById(cid).onclick = function(){};
				} else {
					document.getElementById(cid).innerText = data[j];
					var method = "jumpTo("+data[j]+")";
					document.getElementById(cid).parentNode.onclick = new Function(method);
				}
				j += 4;
			}
			j = pagenum*8*4+1;
			for (var i = 2; i < 17; i += 2) {// name
				var cid = id + i;
				if (typeof (data[j]) == "undefined") {
					document.getElementById(cid).innerText = "";
					document.getElementById(cid).onclick = function(){};
				} else {
					document.getElementById(cid).innerText = data[j];
				}
				j += 4;
			}
	    });
	}
}
