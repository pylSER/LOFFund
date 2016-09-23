
<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.LatestDataVO"%>
<%@page import="bl.RiseFund"%>
<%@page import="bl.LatestData"%>
<%@page import="bl.RateCalculator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bl.FundNameAndCodeGetter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String x = request.getParameter("num");
int rowsPerPage=7;
int pagenum=Integer.parseInt(x);
int start=(pagenum-1)*rowsPerPage;
int stop=pagenum*rowsPerPage-1;


FundNameAndCodeGetter fgetter=new FundNameAndCodeGetter();
ArrayList<String> namecodelist=new ArrayList<String>();
namecodelist=fgetter.getFundNameAndCode();
ArrayList<String> codelist=new ArrayList<String>();

String firstname="";

int i=0;
for(String code:namecodelist){
	if(i==0){
		firstname=code.substring(9);
		code=code.substring(3,9);
		i++;
		
	}else{
		code=code.substring(2,8);
	}
	
	codelist.add(code);	
}
RiseFund rf=new RiseFund();
LatestData ld=new LatestData(); 
ArrayList<Boolean> profitBooleans=rf.isFundProfit(codelist);
JSONArray array=new JSONArray();

RateCalculator calculator=new RateCalculator();
i=0;


for(int k=start;k<=stop;k++){
	if(k>=codelist.size()){
		break;
	}
	
	
	//不可套利箭头就应该向下
	//输入为空时，应该显示全部，点击nav基金时也是
	String code=codelist.get(k);

	//get buyone and predict
	LatestDataVO ldvo= ld.getLatestData(code);
	
	String predict=String.format("%.3f",ldvo.getPredict()); 
	
	String res=calculator.getYesterdayRateAndPrice(code);
	
	String[] arr=res.split(",");
	
	
	String output="";
	//profitable
	if(profitBooleans.get(k)){
		if(k==0){
			Object[] data = {firstname,code,arr[0],arr[1],ldvo.getAsk1(),predict,"1"};
			array.add(data);
//			array.add(firstname);
//			array.add(code);
//			array.add(arr[0]);
//			array.add(arr[1]);
//			array.add(ldvo.getAsk1());
//			array.add(predict);
//			array.add("1");
		}
		
		else{
			Object[] data = {ldvo.getName(),code,arr[0],arr[1],ldvo.getAsk1(),predict,"1"};
			array.add(data);
//			array.add(ldvo.getName());
//			array.add(code);
//			array.add(arr[0]);
//			array.add(arr[1]);
//			array.add(ldvo.getAsk1());
//			array.add(predict);
//			array.add("1");
			
		}
	}// non profitable
	else{
		
		if(k==0){
			Object[] data = {firstname,code,arr[0],arr[1],ldvo.getAsk1(),predict,"0"};
			array.add(data);
//			array.add(firstname);
//			array.add(code);
//			array.add(arr[0]);
//			array.add(arr[1]);
//			array.add(ldvo.getAsk1());
//			array.add(predict);
//			array.add("0");
		}
		
		else{
			Object[] data = {ldvo.getName(),code,arr[0],arr[1],ldvo.getAsk1(),predict,"0"};
			array.add(data);
//			array.add(ldvo.getName());
//			array.add(code);
//			array.add(arr[0]);
//			array.add(arr[1]);
//			array.add(ldvo.getAsk1());
//			array.add(predict);
//			array.add("0");
		}
	}
	i++;
	
}
out.print(array);
%>
    