package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import blService.SearchService;
import vo.SearchVO;

public class SearchEngine implements SearchService {
static ArrayList<String> codelist= new ArrayList<String>();
static ArrayList<String> namelist=new ArrayList<String>();
static ArrayList<String> typelist=new ArrayList<String>();


static boolean isInited=false;
String filepath="D:\\Competiton\\花旗杯\\lofdata\\zj\\test.txt";

public SearchEngine(){
	if (!isInited) {
		getReady();
		isInited=true;
	}
}



private ArrayList<String> adviceAll(String input){
	ArrayList<String> reslist=new ArrayList<String>();
	if (input.length()==0) {
		return reslist;
	}

	int inputlen=input.length();
	
	
	if(input.charAt(0)=='5'){
	for (String string : codelist) {
		if (inputlen>string.length()) {
		
			continue;
		}
		
		if(string.substring(0,inputlen).equals(input)){
			
				System.out.println(string);
				reslist.add(string);
			
			
		}
		
	}
	
	
	
	for (String string : namelist) {
		if (inputlen>string.length()) {
			continue;
		}
		if(string.substring(0,inputlen).equals(input)){
			
				System.out.println(string);
				reslist.add(string);
			
		}
	}
	return reslist;
	}
	

	if(input.charAt(0)>='0'&&input.charAt(0)<='9'){
		for (String string : codelist) {
			if (inputlen>string.length()) {
				
				continue;
			}
			
			if(string.substring(0,inputlen).equals(input)){
				
				reslist.add(string);
				
			}
			
		}	
		
		return reslist;
	}else {
		for (String string : namelist) {
			if (inputlen>string.length()) {
				
				continue;
			}
			
			if(string.substring(0,inputlen).equals(input)){
			
					reslist.add(string);
				
			}
		}
		return reslist;
	}
	
}








private ArrayList<String> advice(String input,String type){
	if(type.equals("0")){
		return adviceAll(input);
	}
	
	
	
	ArrayList<String> reslist=new ArrayList<String>();
	if (input.length()==0) {
		return reslist;
	}

	int inputlen=input.length();
	
	int counter=0;
	
	if(input.charAt(0)=='5'){
	for (String string : codelist) {
		if (inputlen>string.length()) {
			counter++;
			continue;
		}
		
		if(string.substring(0,inputlen).equals(input)){
			if(type.equals(typelist.get(counter))){
				System.out.println(string);
				reslist.add(string);
			}
			
		}
		counter++;
	}
	
	
	counter=0;
	
	for (String string : namelist) {
		if (inputlen>string.length()) {
			counter++;
			continue;
		}
		if(string.substring(0,inputlen).equals(input)){
			if(type.equals(typelist.get(counter))){
				System.out.println(string);
				reslist.add(string);
			}
		}
		counter++;
	}
	return reslist;
	}
	
	
	counter=0;
	if(input.charAt(0)>='0'&&input.charAt(0)<='9'){
		for (String string : codelist) {
			if (inputlen>string.length()) {
				counter++;
				continue;
			}
			
			if(string.substring(0,inputlen).equals(input)){
				if(type.equals(typelist.get(counter))){
				reslist.add(string);
				}
			}
			counter++;
		}	
		
		return reslist;
	}else {
		for (String string : namelist) {
			if (inputlen>string.length()) {
				counter++;
				continue;
			}
			
			if(string.substring(0,inputlen).equals(input)){
				if(type.equals(typelist.get(counter))){
					reslist.add(string);
				}
			}
			counter++;
		}
		return reslist;
	}
	
}






	private void getReady() {
		File file=new File(filepath);
		
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			
			String text="";
			
			while((text = br.readLine()) != null){
				String type="";
				type+=text.charAt(0);
				
				typelist.add(type);
				
				String code=text.substring(3, 9);
				
				codelist.add(code);
				int len=text.length();
				String name=text.substring(9,len);
				name=name.trim();
				
				
				namelist.add(name);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
//debug mode		System.out.println(typelist);
//		
//		System.out.println(codelist);
//		
//		System.out.println(namelist);
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		SearchEngine searchEngine=new SearchEngine();
//		System.out.println(searchEngine.advice(input, type));
		
		System.out.println(searchEngine.getSearchInfo("165","3"));
//		System.out.println(SearchEngine.codelist);
//		System.out.println(SearchEngine.namelist);
		
	}
	
	
	
	
	public ArrayList<SearchVO> getSearchInfo(String input,String type){
		
		
		ArrayList<SearchVO> reslist=new ArrayList<SearchVO>();
		ArrayList<String> mixlist=advice(input,type);
		
		for (String string : mixlist) {
			SearchVO vo=new SearchVO();
			if (isInteger(string)) {
				//get name set code
				int pos=codelist.indexOf(string);
				vo.setName(namelist.get(pos));
				vo.setCode(string);			
			}else {
				//get code set name
				int pos=namelist.indexOf(string);
				vo.setCode(codelist.get(pos));
				vo.setName(string);	
			}
			
			//get two other feature
			RateCalculator calculator=new RateCalculator();
			String reString=calculator.getYesterdayRateAndPrice(vo.getCode());
			
			String[] arr=reString.split(",");
			
			vo.setYesterdayPrice(arr[0]);
			vo.setYesterdayRate(arr[1]);
			
			reslist.add(vo);
		}
		return reslist;
	}
	


	  private boolean isInteger(String str) {  
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	    return pattern.matcher(str).matches();  
	  }

	  public String getName(String code){
		  int cursor=0;
		  for(cursor=0;cursor<codelist.size();cursor++){
			  if(code.equals(codelist.get(cursor))){
				  break;
			  }
		  }
		  return namelist.get(cursor);
	  }
	
	
	
	
}
