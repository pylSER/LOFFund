<%@page import="vo.SearchVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bl.SearchEngine"%>
<%@page import="blService.SearchService"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
String x = request.getParameter("name");
String type = request.getParameter("type");


SearchService service=new SearchEngine();
ArrayList<SearchVO> list=service.getSearchInfo(x,type);

JSONArray arr=new JSONArray();
for(SearchVO vo:list){
	arr.add(vo.getCode());
	arr.add(vo.getName());
	arr.add(vo.getYesterdayPrice());
	arr.add(vo.getYesterdayRate());
}

out.print(arr);



%>
