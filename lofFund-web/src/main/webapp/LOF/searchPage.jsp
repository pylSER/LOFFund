<%@page import="vo.LatestDataVO"%>
<%@page import="bl.SearchEngine"%>
<%@page import="bl.RiseFund"%>
<%@page import="bl.LatestData"%>
<%@page import="bl.ResrowBuilder"%>
<%@page import="vo.SearchVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="blService.SearchService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN" >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>LOF Index</title>

    <!-- Bootstrap -->
    
    
    
  
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/mycss.css" rel="stylesheet">
    <link href="bootstrap/css/SearchPage.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="bootstrap/css/jquery.bs_pagination.bs2.min.css">
  
    <link href="bootstrap/css/navbar.css" rel="stylesheet">

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrap/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/en.min.js"></script>

<script src="bootstrap/js/jump.js"></script>
<script src="bootstrap/js/shift.js"></script>


    <![endif]-->
</head>


<nav class="navbar navbar-default navbar-fixed-top" style="background-color: #676767;">
    <div class="container-fluid">
<!-- Brand and toggle get grouped for better mobile display -->
<div class="navbar-header">
    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
     <a class="navbar-brand " href="#" style="margin-left: 50px;margin-right: 50px;margin-top:5px">
                <img src="bootstrap/loflogo.svg" style="width: 65px">
            </a>
</div>

<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav myul">
        <li id="navli1" style="padding-bottom: 10px;padding-top: 10px" onclick="gotoIndex()"><a href="#" style="font-size: 20px;color: #ffffff;">&nbsp;首页&nbsp;<span class="sr-only">(current)</span></a></li>
        <li id="navli2" style="background-color: #575757;padding-bottom: 10px;padding-top: 10px" onclick="gotoFund()"><a href="#" style="font-size: 20px;color: #ffffff;">&nbsp;基金&nbsp;</a></li>
        <li id="navli3" style="padding-bottom: 10px;padding-top: 10px" onclick="gotoDescription()"><a href="#" style="font-size: 20px;color: #ffffff;">&nbsp;算法简介&nbsp;</a></li>
    </ul>
</div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>







<body style="position:relative;padding-bottom:50px; padding-top: 125px; font-family: PingFang">
<div id="loading" style="position: absolute;background-color: white;opacity: 0.8;left: 0px;top: 0px;z-index: 1500;height: 120%;width: 100%;text-align: center;display: none;justify-content: center">
    <embed src="bootstrap/bars.svg" width="60px">
</div>




<div class="row">
    <div class="col-md-2"></div>

    <div class="col-md-8">
        <div class="col-md-2"></div>

        <div class="input-group" style="border: solid 1px #676767;">


        <!-- Single button -->
        <div class="btn-group" style="float: left;background-color: #676767;">
            <button id="btnselect" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color: #676767;border-radius: 0px;height: 47px;width: 75px;border: 0px;color: white">
                全部 <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" style="background-color: #676767;border-radius: 0px;min-width: 0px;">
                <li onclick="selectType(0)"><a href="#" style="color: white">全部</a></li>
                <li onclick="selectType(1)"><a href="#" style="color: white">综合</a></li>
                <li onclick="selectType(2)"><a href="#" style="color: white">偏股</a></li>
                <li onclick="selectType(3)"><a href="#" style="color: white">偏债</a></li>
            </ul>
        </div>


        <input id="searchinput" type="text" class="form-control" autocomplete="off" onkeydown='if(event.keyCode==13){gosearch()}' placeholder="键入基金代码或者名称..." style="width: 600px;height: 47px;background-color: #fafafa;border: 0px;border-radius: 0px">


        <button class="btn btn-default" type="button" onclick="gosearch()" style="background-color: #fafafa;height: 47px;width: 47px;border: 0px;border-radius: 0px">
            <img src="bootstrap/search.png">
        </button>

        </div>



    </div>

    <div class="col-md-2"></div>


</div>


<div class="row" style="z-index: 999">
    <div class="col-md-2"></div>

    <div class="col-md-9">
        <span class="col-md-2"></span>
        <table cellpadding='2' cellspacing='0' id="searchtable">
            <tbody>
            <th class="row" id="tabletitle">
                <div class="col-md-3" style="border-right: solid 2px #a7a7a7">基金代码</div>
                <div class="col-md-3" style="border-right: solid 2px #a7a7a7">基金名称</div>
                <div class="col-md-3" style="border-right: solid 2px #a7a7a7">昨日价格</div>
                <div class="col-md-3">昨日涨跌幅</div>

            </th>
            <tr><td class="td1"></td></tr>
            <tr><td class="td2"></td></tr>
            <tr><td class="td1"></td></tr>
            <tr><td class="td2"></td></tr>
            <tr><td class="td1"></td></tr>
            <tr><td class="td2"></td></tr>
            <tr><td class="td1"></td></tr>
            <tr><td class="td2"></td></tr>
            <tr><td class="td1"></td></tr>
            <tr><td class="td2"></td></tr>
            </tbody>
        </table>

    </div>

</div>

<div style="margin-top:45px"></div>

<%
String x = request.getParameter("name");
String type = request.getParameter("type");
SearchService service=new SearchEngine();
ArrayList<SearchVO> list=service.getSearchInfo(x,type);

ResrowBuilder builder=new ResrowBuilder();
LatestData ld=new LatestData(); 
RiseFund rf=new RiseFund();

ArrayList<String> codeList=new ArrayList<String>();
for(SearchVO vo:list){
	codeList.add(vo.getCode());
}
ArrayList<Boolean> profitBooleans=rf.isFundProfit(codeList);

int i=0;
for(SearchVO vo:list){
	//不可套利箭头就应该向下
	//输入为空时，应该显示全部，点击nav基金时也是
	if(i==7){
		break;
	}
	//get buyone and predict
	LatestDataVO ldvo= ld.getLatestData(vo.getCode());
	
	String predict=String.format("%.3f",ldvo.getPredict()); 
	
	String output="";
	//profitable
	if(profitBooleans.get(i)){
		output=builder.buildRow(vo.getName(), vo.getCode(), vo.getYesterdayPrice(), vo.getYesterdayRate(), ldvo.getAsk1(), predict,i);
	}// non profitable
	else{
		output=builder.buildRowForBad(vo.getName(), vo.getCode(), vo.getYesterdayPrice(), vo.getYesterdayRate(), ldvo.getAsk1(), predict,i);
	}
	i++;
	out.print(output);
}


%>



<span id="numoflist" style="display:none">
<%
out.print(list.size());
%>
</span>

<span id="searchname" style="display:none">
<%
out.print(x);
%>
</span>


<span id="searchtype" style="display:none">
<%
out.print(type);
%>
</span>


<div id="demo_pag1" style="margin-top:20px;"></div>

<script>
    $(function() {
		var pages=document.getElementById("numoflist").innerText;
	
		
		var a=pages/7;
		a=Math.ceil(a);
		
		var name=document.getElementById("searchname").innerText;
		
		var type=document.getElementById("searchtype").innerText;
		
        $("#demo_pag1").bs_pagination({
        	navListContainerClass: "col-md-offset-2",
            containerClass: "",
            showRowsPerPage: false,
            showGoToPage: false,
            showRowsInfo: false,
            rowsPerPage: 7,
            totalPages: a,
            onChangePage: function(event,data) { // returns page_num and rows_per_page after a link has clicked
            	document.getElementById("loading").style.height=document.body.scrollHeight;
            	document.getElementById("loading").style.display="flex";
            	changePageForSearch(data.currentPage,name.trim(),type.trim());
            
            }
        });

    });
</script>



<script src="bootstrap/js/forsearch.js"></script>

<div style="padding-top: 54px"></div>


</body>
<footer style="position:absolute; bottom: 0px;width: 100%; font-family: PingFang">
    <div class="panel-footer" style="text-align: center;background-color: #eeeeee;">
        <span style=";font-size: 16px;letter-spacing: 0.5px;color: #676767;">
            Copyright&copy;LOF基金套利&nbsp;
        </span>
        <span style="color: #9e9e9e;font-size: 16px">
            基于人工智能
        </span>
    </div>
</footer>
</html>