<%@page import="bl.RateCalculator"%>
<%@page import="bl.ResrowBuilder"%>
<%@page import="java.util.Collections"%>
<%@page import="vo.RiseVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bl.RiseFund"%>
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

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <link href="bootstrap/css/navbar.css" rel="stylesheet">

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
RiseFund rf=new RiseFund();
ArrayList<RiseVO> list= rf.getRiseFund();
Collections.sort(list);
ResrowBuilder builder=new ResrowBuilder();
RateCalculator cal=new RateCalculator();
int i=0;
for(RiseVO vo:list){
	double predict=vo.getPredict();
	String rateandp=cal.getYesterdayRateAndPrice(vo.getCode());
	String[] arr=rateandp.split(",");
	
	String p=String.format("%.3f",predict); 
	String res=builder.buildRow(vo.getName().trim(), vo.getCode(), arr[0], arr[1], vo.getAsk1(),p,i);
	out.print(res);
	i++;
}

	

	


%>






<script src="bootstrap/js/forsearch.js"></script>

<div style="padding-top: 54px"></div>

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/jump.js"></script>
<script src="bootstrap/js/getFundReady.js"></script>
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