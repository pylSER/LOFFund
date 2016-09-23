<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="vo.BackTestVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="predict.BackTest"%>
<%@page import="blService.BackTestService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String x = request.getParameter("name");
BackTestService service=new BackTest(); 


ArrayList<BackTestVO> reslist=service.backTest(x, 20);
JSONArray arr = new JSONArray();

for(BackTestVO vo:reslist){
	String date=vo.getDate();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date d = formatter.parse(date);
	
	double[] data={d.getTime(),vo.getReal(),vo.getPredict()};
	arr.add(data);
}
out.print(arr);

%>