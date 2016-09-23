<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.LatestDataVO"%>
<%@page import="bl.SearchEngine"%>
<%@page import="bl.RiseFund"%>
<%@page import="bl.LatestData"%>
<%@page import="vo.SearchVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="blService.SearchService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String xnum= request.getParameter("num");
String x = request.getParameter("name");
String type = request.getParameter("type");

int rowsPerPage=7;
int pagenum=Integer.parseInt(xnum);
int start=(pagenum-1)*rowsPerPage;
int stop=pagenum*rowsPerPage-1;

JSONArray array=new JSONArray();
SearchService service=new SearchEngine();
ArrayList<SearchVO> list=service.getSearchInfo(x,type);

System.out.print(list.size());

LatestData ld=new LatestData(); 
RiseFund rf=new RiseFund();

ArrayList<String> codeList=new ArrayList<String>();
for(SearchVO vo:list){
	codeList.add(vo.getCode());
}
ArrayList<Boolean> profitBooleans=rf.isFundProfit(codeList);

for(int k=start;k<=stop;k++){
	if(k>=list.size()){
		break;
	}
	
	
	
	SearchVO vo=list.get(k);
	//不可套利箭头就应该向下
	//输入为空时，应该显示全部，点击nav基金时也是
	//get buyone and predict
	LatestDataVO ldvo= ld.getLatestData(vo.getCode());
	
	String predict=String.format("%.3f",ldvo.getPredict()); 
	
	String output="";
	//profitable
	if(profitBooleans.get(k)){
		Object[] data = {vo.getName(),vo.getCode(),vo.getYesterdayPrice(),vo.getYesterdayRate(),ldvo.getAsk1(),predict,"1"};
		array.add(data);
//		array.add(vo.getName());
//		array.add(vo.getCode());
//		array.add(vo.getYesterdayPrice());
//		array.add(vo.getYesterdayRate());
//		array.add(ldvo.getAsk1());
//		array.add(predict);
//		array.add("1");
	}// non profitable
	else{
		Object[] data = {vo.getName(),vo.getCode(),vo.getYesterdayPrice(),vo.getYesterdayRate(),ldvo.getAsk1(),predict,"0"};
		array.add(data);
//		array.add(vo.getName());
//		array.add(vo.getCode());
//		array.add(vo.getYesterdayPrice());
//		array.add(vo.getYesterdayRate());
//		array.add(ldvo.getAsk1());
//		array.add(predict);
//		array.add("0");
	}
}
out.print(array);

%>
    