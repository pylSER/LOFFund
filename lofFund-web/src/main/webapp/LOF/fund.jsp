<%@page import="text.Trend"%>
<%@page import="blService.TrendService"%>
<%@page import="bl.FundNameAndCodeGetter"%>
<%@page import="bl.SearchEngine"%>
<%@page import="blService.SearchService"%>
<%@ page import="bl.RateCalculator" %>
<%@ page import="bl.RiseFund" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bl.LatestData" %>
<%@ page import="vo.LatestDataVO" %>
<%@ page import="predict.PredictR" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>LOF Index</title>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="bootstrap/js/en.min.js"></script>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/pylfund.css" rel="stylesheet">
    <link href="bootstrap/css/hmt.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <link href="bootstrap/css/navbar.css" rel="stylesheet">

    <![endif]-->
</head>


<nav class="navbar navbar-default navbar-fixed-top" style="background-color: #676767;font-family: PingFang">
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







<body style="position:relative; padding-bottom:50px; padding-top: 125px;font-family: PingFang">

<%
TrendService t = new Trend();
double[] tr = t.getTrend();

int max=0;

for(int i=0;i<3;i++){
	if(tr[max]<tr[i]){
		max=i;
	}
}

String up=String.format("%.2f",tr[0]*100);
String mid=String.format("%.2f",tr[1]*100);
String down=String.format("%.2f",tr[2]*100);
up+="%";
mid+="%";
down+="%";
out.print("");

if(max==0||max==1){
	out.print("<div id=\"yulunrow\" class=\"row\" style=\"height: 42px;margin-bottom: 5px;\"><div class=\"col-md-2\"></div><div id=\"yt\" class=\"col-md-8 bar1\">");
	out.print("<span class=\"yuluntitle\">综合各大舆论平台对股市的预测文本分析</span>");
	
	out.print("<span  class=\"yulunnum\">"+up+"</span>");
	out.print("<span class=\"yulunstr\">上涨</span>");
	out.print("<span  class=\"yulunnum\">"+mid+"</span>");
	out.print("<span class=\"yulunstr\">中立</span>");
	out.print("<span  class=\"yulunnum\">"+down+"</span>");
	out.print("<span class=\"yulunstr\">下跌</span>");

	out.print(" <span class=\"conclusion\">大环境适合基金套利</span>");
	out.print("<span class=\"glyphicon glyphicon-remove\" style=\"float: right;padding-top: 5px;cursor: pointer;color:#4ba3d3\" onclick=\"closerow()\"></span></div></div>");

}else{
	out.print("<div id=\"yulunrow\" class=\"row\" style=\"height: 42px;margin-bottom: 5px;\"><div class=\"col-md-2\"></div>");
	out.print("<div id=\"yt\" class=\"col-md-8 bar2\"><span class=\"badyuluntitle\">综合各大舆论平台对股市的预测文本分析</span>");
	
	out.print("<span  class=\"badyulunnum\">"+up+"</span>");
	out.print("<span class=\"badyulunstr\">上涨</span>");
	out.print("<span  class=\"badyulunnum\">"+mid+"</span>");
	out.print("<span class=\"badyulunstr\">中立</span>");
	out.print("<span  class=\"badyulunnum\">"+down+"</span>");
	out.print("<span class=\"badyulunstr\">下跌</span>");

	out.print(" <span class=\"badconclusion\">大环境不适合基金套利</span>");
	out.print("<span class=\"glyphicon glyphicon-remove\" style=\"float: right;padding-top: 5px;cursor: pointer;color:#575757\" onclick=\"closerow()\"></span></div></div>");

	
}


%>




<div class="row" style="height: 700px;">


    <div class="col-md-2"></div>

    <div id="block1"  class="col-md-2" style="background-color:#dfdfdf;height: 513px;min-width: 290px;padding-left: 0px;padding-right: 0px">

      <div class="row" style="background-color: #969696;height: 62px;width: 100%;margin-left:0;margin-left:0">
		

            <div class="input-group" style="border: solid 1px #676767;margin-top: 8px; width:68%;margin-left:16%">


                <input id="smallsearch" type="text" class="form-control" autocomplete="off" onkeydown='if(event.keyCode==13){smallgosearch(0,1)}'  style="width:75%;height: 44px;background-color: #fafafa;border: 0px;border-radius: 0px">


                <button class="btn btn-default" type="button" onclick="smallgosearch(0,1)" style="background-color: #fafafa;width:25%;height:44px;border: 0px;border-radius: 0px">
                    <img src="bootstrap/search.png">
                </button>

            </div>


        </div>


        <div class="row smalltitle">
            <div style="height: 10px"></div>
            <div class="col-md-6" style="border-right:solid 2px #7b7b7b ">基金代码</div>
            <div class="col-md-6">基金名称</div>
        </div>


<%
FundNameAndCodeGetter fgetter=new FundNameAndCodeGetter();
ArrayList<String> namecodelist=new ArrayList<String>();
namecodelist=fgetter.getFundNameAndCode();
String td2="<td class=\"td2\" onclick=\"jumpTo(";
String td1="<td class=\"td1\" onclick=\"jumpTo(";
String back=")\">";
%>



        <table style="width: 100%">
            <tbody>
            <tr><%out.print(td2+namecodelist.get(0).substring(3,9)+back); %>
                <div class="col-md-6" id="sbar1"><%=namecodelist.get(0).substring(3,9)%></div>
            	<div class="col-md-6" id="sbar2"><%=namecodelist.get(0).substring(9)%></div>
            </td></tr>
            <tr><%out.print(td1+namecodelist.get(1).substring(2,8)+back); %>
                <div class="col-md-6" id="sbar3"><%=namecodelist.get(1).substring(2,8)%></div>
            	<div class="col-md-6" id="sbar4"><%=namecodelist.get(1).substring(8)%></div>
            </td></tr>
            <tr><%out.print(td2+namecodelist.get(2).substring(2,8)+back); %>
            	<div class="col-md-6" id="sbar5"><%=namecodelist.get(2).substring(2,8)%></div>
            	<div class="col-md-6" id="sbar6"><%=namecodelist.get(2).substring(8)%></div>
            </td></tr>
            <tr><%out.print(td1+namecodelist.get(3).substring(2,8)+back); %>
                <div class="col-md-6" id="sbar7"><%=namecodelist.get(3).substring(2,8)%></div>
            	<div class="col-md-6" id="sbar8"><%=namecodelist.get(3).substring(8)%></div>
            </td></tr>
            <tr><%out.print(td2+namecodelist.get(4).substring(2,8)+back); %>
                <div class="col-md-6" id="sbar9"><%=namecodelist.get(4).substring(2,8)%></div>
            	<div class="col-md-6" id="sbar10"><%=namecodelist.get(4).substring(8)%></div>
            </td></tr>
            <tr><%out.print(td1+namecodelist.get(5).substring(2,8)+back); %>
                <div class="col-md-6" id="sbar11"><%=namecodelist.get(5).substring(2,8)%></div>
            	<div class="col-md-6" id="sbar12"><%=namecodelist.get(5).substring(8)%></div>
            </td></tr>
            <tr><%out.print(td2+namecodelist.get(6).substring(2,8)+back); %>
                <div class="col-md-6" id="sbar13"><%=namecodelist.get(6).substring(2,8)%></div>
            	<div class="col-md-6" id="sbar14"><%=namecodelist.get(6).substring(8)%></div>
            </td></tr>
            <tr><%out.print(td1+namecodelist.get(7).substring(2,8)+back); %>
                <div class="col-md-6" id="sbar15"><%=namecodelist.get(7).substring(2,8)%></div>
            	<div class="col-md-6" id="sbar16"><%=namecodelist.get(7).substring(8)%></div>
            </td></tr>
            <tr><td class="pagin">
            <div id="demo_pag1"></div>
            </td>
            </tr>
            </tbody>
        </table>
    </div>

 <span id="hiddeninfo" style="display:none"></span>
 
<script>
    $(function() {
        $("#demo_pag1").bs_pagination({
        	mainWrapperClass: "",
        	navListContainerClass: "",
            containerClass: "",
            showRowsPerPage: false,
            showGoToPage: false,
            showRowsInfo: false,
            rowsPerPage: 7,
            visiblePageLinks: 3,
            totalPages: <%=Math.ceil(namecodelist.size()/8.0)%>,
            onChangePage: function(event,data) { // returns page_num and rows_per_page after a link has clicked
            	
            	smallgosearch(1,data.currentPage);
            }
        });

    });
</script>








    <div id="block2"  class="col-md-6" style="height: 600px;margin-left: 31px;padding:0px;background-color: #f9f9f9;">
        <div class="row">
            <div class="col-md-5" style="margin-left: 4.5%;margin-top: 19px;">
                <div style="font-size: 30px;letter-spacing: 2px;color: #676767;">
                    <%
                    String x = request.getParameter("code");
                    SearchService service = new SearchEngine();
                    String outputval = "<span>";
							outputval += service.getName(x);
							outputval += "&nbsp;</span>";
                    outputval += "<span id=\"code\">";
							outputval += x;
							outputval += "</span>";
                    out.print(outputval);
                    %>
                </div>
            </div>
            <div class="col-md-1">     </div>

            <div class="col-md-5" style="margin-top: 26px;padding-left: 40px;padding-right: 0px">
                <span style="font-size: 18px;letter-spacing: 0.5px;color: #676767;padding-right: 10px;">预测价格:</span>
                <%
                    out.print("<span style=\"font-size: 22px;letter-spacing: 0.6px;color:");
                    LatestData data = new LatestData();
                    LatestDataVO vo = data.getLatestData(x);
                    String pre = String.format("%.3f",vo.getPredict());

                    String st1 = "<span style=\"height: 14px;\"> <img src=\"bootstrap/";
                    String st2 = "arrow.svg\" style=\"height: 20px;width: 20px;padding-bottom: 5px\"></span>";
                    RiseFund riseFund = new RiseFund();
                    ArrayList<String> arr = new ArrayList<String>();
                    arr.add(x);
                    ArrayList<Boolean> ispro = riseFund.isFundProfit(arr);
                    if(ispro.get(0)){
                        out.print(" #dc1c10;\">");
                        out.print(pre);
                        out.print("</span>");
                        out.print(st1+"red"+st2+"<div   class=\"profiticon\"><div style=\"margin-top: 3px\">可套利</div>"+"</div>");
                    }else{
                        out.print(" green;\">");
                        out.print(pre);
                        out.print("</span>");
                        out.print(st1+"green"+st2);
                    }
                %>
                <%--<span style="font-size: 22px;letter-spacing: 0.6px;color: #dc1c10;">3.00</span>--%>
                <%--<span style="height: 14px;"> <img src="bootstrap/redarrow.svg" style="height: 20px;width: 20px;padding-bottom: 5px"></span>--%>
                <%--<div   class="profiticon"><div style="margin-top: 2px">可套利</div>--%>
                <%--</div>--%>
            </div>
        </div>

        <div  style="width: 91%;height:98px;background: white;margin-left: 4.5%;margin-top: 10px;padding: 0;">
            <div class="row">
           <div class="col-md-1" style="width:10px"></div>


            <div class="col-md-2 subtitle">
                昨日价格
            </div>

            <div class="col-md-2 subtitle">
                昨日涨跌
            </div>

            <div class="col-md-2 subtitle" >
                交易额
            </div>

            <div class="col-md-2 subtitle">
                卖一价
            </div>

            <div class="col-md-3 subtitle">
                套利区间
            </div>
            
            </div>






            <div class="row">
                <div class="col-md-1" style="width:10px"></div>

				<%
				    String code=request.getParameter("code");
                    String s1 = "<div class=\"col-md-2 subnumber\">";
                    String s2 = "<div class=\"col-md-3 subnumber\">";
                    String s3 = "</div>";
                    String s4 = "<div class=\"col-md-2 subnumber\">";

                    RateCalculator rateCalculator = new RateCalculator();
                    String res = rateCalculator.getYesterdayRateAndPrice(code);
                    String[] rp = res.split(",");
                    out.print(s1+rp[0]+s3);
                    out.print(s1+rp[1]+s3);

                    out.print(s1+vo.getVolume()/10000+"万"+s3);
				    out.print(s4+vo.getAsk1()+s3);
                    if(ispro.get(0)) {
                        out.print(s2 + "[" + String.format("%.4f",vo.getAsk1() * 1.006) + "," + String.format("%.4f",vo.getPredict()) + "]" + s3);
                    }else{
                        out.print(s1+"--"+s3);
                    }
				%>
				
				

                <%--<div class="col-md-2 subnumber">--%>
                    <%--16.00--%>
                <%--</div>--%>

                <%--<div class="col-md-3 subnumber">--%>
                    <%--16.00--%>
                <%--</div>--%>

                <%--<div class="col-md-2 subnumber" >--%>
                    <%--16.00--%>
                <%--</div>--%>

                <%--<div class="col-md-2 subnumber">--%>
                    <%--16.00--%>
                <%--</div>--%>

                <%--<div class="col-md-2 subnumber">--%>
                    <%--16.00--%>
                <%--</div>--%>



                
            </div>
        </div>

        <div style="padding-top:30px;margin-left: 4.5%;">
            <span class="glyphicon glyphicon-stop" aria-hidden="true" style="height:14px;color: #dc1c10;"></span>
            <span style="height: 30px;font-family: PingFangSC,'Microsoft Yahei';font-size: 22px;letter-spacing:1px;color: #676767;">
            30天回测</span>
        </div>



        <div id="chartplace" style="margin-top:10px;margin-left: 4.5%;margin-right: 4.5%;height: 330px;border: solid 0px;">

        </div>
    </div>

    <div class="col-md-2"></div>


</div>


<!--<span class="glyphicon glyphicon-search form-control-feedback" style="color: #dc1c10;font-size: 30px;"></span>-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/pagechange.js"></script>
<script src="bootstrap/js/jump.js"></script>
<script src="bootstrap/js/drawchart.js"></script>
<script src="bootstrap/js/getFundJSPReady.js"></script>


<script src="https://code.highcharts.com/stock/highstock.js"></script>

<script src="//cdn.bootcss.com/highcharts/4.2.6/modules/exporting.js"></script>
</body>
<footer style="position:absolute; bottom: 0px;width: 100%;">
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

	