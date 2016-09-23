<%@ page import="bl.FundInfoGetter" %>
<%@ page import="vo.FundDateDataVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="blService.GMBackTestService" %>
<%@ page import="benchmark.GMBackTest" %>
<%@ page import="vo.BackTestVO" %><%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2016/9/9
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    //int days = Integer.parseInt(request.getParameter("days"));
    FundInfoGetter fundinfo = new FundInfoGetter();
    ArrayList<FundDateDataVO> datavolist = fundinfo.getFundInfo("000001",33);
    GMBackTestService gbt = new GMBackTest();
    ArrayList<BackTestVO> btarr = gbt.backTest(33);

    JSONArray arr=new JSONArray();
    for(int i = datavolist.size()-1;i>=0;i--){

        if(datavolist.get(i).getOpen()==1){
            continue;
        }
        String date = datavolist.get(i).getDate();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        Date d = formatter.parse(date);

//        String date2 = btarr.get(btarr.size()-1-i).getDate();
//        Date d2 = formatter.parse(date2);
        
        double[] data = {d.getTime(),datavolist.get(i).getOpen(),datavolist.get(i).getHigh(),
                datavolist.get(i).getLow(),datavolist.get(i).getClose(),btarr.get(btarr.size()-1-i).getPredict()};

        arr.add(data);
    }

    out.print(arr);
%>

