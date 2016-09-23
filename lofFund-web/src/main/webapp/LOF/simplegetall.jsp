<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bl.FundNameAndCodeGetter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%
	FundNameAndCodeGetter fgetter=new FundNameAndCodeGetter();
	ArrayList<String> namecodelist=new ArrayList<String>();
	namecodelist=fgetter.getFundNameAndCode();
	JSONArray array=new JSONArray();
	
	boolean isFirst=true;
	int i=0;
	//code name code name
	for(String nc:namecodelist){
		if(isFirst){
			array.add(namecodelist.get(i).substring(3,9));
			array.add(namecodelist.get(i).substring(9));
			isFirst=false;
		}else{
			array.add(namecodelist.get(i).substring(2,8));
			array.add(namecodelist.get(i).substring(8));
		}
		i++;
	}
	
	out.print(array);
	%>