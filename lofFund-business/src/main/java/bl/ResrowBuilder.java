package bl;

public class ResrowBuilder {
	private String spine;
	
	
	public String buildRow(String name,String code,String yesterday,String rate,double buyone1,String predict,int num) {
		String buyone=String.format("%.3f",buyone1);
		
		String nameid=" id=\"name";
		nameid+=num;
		nameid+="\" ";
		
		
		String codeid=" id=\"code";
		codeid+=num;
		codeid+="\" ";
		
		
		String yesid=" id=\"yesterday";
		yesid+=num;
		yesid+="\" ";
		
		
		String rateid=" id=\"rate";
		rateid+=num;
		rateid+="\" ";
		
		String buyoneid=" id=\"buyone";
		buyoneid+=num;
		buyoneid+="\" ";
		
		String predictid=" id=\"predict";
		predictid+=num;
		predictid+="\" ";
		
		String picid=" id=\"pic";
		picid+=num;
		picid+="\" ";
		
		
		spine="<div class=\"row resrow\" > <div class=\"col-md-2\"></div>"
				+ "<div class=\"col-md-8 resbar\" onclick=\"jumpTo("+code
				+ ")\" style=\"padding-left: 0px;padding-right: 0px\">"
				+ " <div class=\"two\" style=\"padding-left: 35px;padding-top: 8px;\">"
				+ "<div class=\"titlefont\""+nameid+">";
		String temp=spine;
		temp+=name;
		temp+="</div><span class=\"profiticon\"><div style=\"margin-top: 2px\">可套利</div></span><div class=\"titlenumfont\""+codeid+">";
		temp+=code;
		temp+="</div></div> <div class=\"three\"><div class=\"col-md-3\"><img src=\"bootstrap/yesterday.svg\" style=\"height: 36px;margin-top: 20px\"/>";
		temp+="</div><div class=\"bardata col-md-4\" style=\"padding-top: 23px;padding-left: 0px\""+yesid+">";
		temp+=yesterday;
		temp+="</div><div class=\"bardata col-md-4\" style=\"padding-top: 23px;padding-left: 0px\""+rateid+">";
		temp+=rate;
		temp+="</div></div><div class=\"three\"><div class=\"col-md-4\"><img src=\"bootstrap/buyone.svg\" style=\"height: 36px;margin-top: 20px\"/></div><div class=\"bardata col-md-5\" style=\"padding-top: 23px;padding-left: 0px\""+buyoneid+">";
		temp+=buyone;
		temp+="</div></div><div class=\"three\"> <div class=\"col-md-4\"><img src=\"bootstrap/predict.svg\" style=\"height: 36px;margin-top: 20px\"/> </div>";
		temp+="<div class=\"bardata col-md-5\" style=\"padding-top: 23px;padding-left: 0px;\""+predictid+">";
		temp+=predict;
		temp+="</div><div class=\"col-md-2\" style=\"padding-left: 0px\""+picid+">";
		temp+="<img src=\"bootstrap/redarrow.svg\" style=\"height: 18px;margin-top: 27px\"/>";
		temp+="</div></div></div><div class=\"col-md-2\"></div></div>";
		
		return temp;
	}
	
	
	public String buildRowForBad(String name,String code,String yesterday,String rate,double buyone1,String predict,int num) {
		String buyone=String.format("%.3f",buyone1);
		String nameid=" id=\"name";
		nameid+=num;
		nameid+="\" ";
		
		
		String codeid=" id=\"code";
		codeid+=num;
		codeid+="\" ";
		
		
		String yesid=" id=\"yesterday";
		yesid+=num;
		yesid+="\" ";
		
		
		String rateid=" id=\"rate";
		rateid+=num;
		rateid+="\" ";
		
		String buyoneid=" id=\"buyone";
		buyoneid+=num;
		buyoneid+="\" ";
		
		String predictid=" id=\"predict";
		predictid+=num;
		predictid+="\" ";
		
		String picid=" id=\"pic";
		picid+=num;
		picid+="\" ";
		
		
		spine="<div class=\"row resrow\" > <div class=\"col-md-2\"></div>"
				+ "<div class=\"col-md-8 resbar\" onclick=\"jumpTo("+code
				+ ")\" style=\"padding-left: 0px;padding-right: 0px\">"
				+ " <div class=\"two\" style=\"padding-left: 35px;padding-top: 8px;\">"
				+ "<div class=\"titlefont\""+nameid+">";
		String temp=spine;
		temp+=name;
		temp+="</div><span class=\"profiticon\" style=\"display:none\"><div style=\"margin-top: 2px;\">可套利</div></span><div class=\"titlenumfont\""+codeid+">";
		temp+=code;
		temp+="</div></div> <div class=\"three\"><div class=\"col-md-3\"><img src=\"bootstrap/yesterday.svg\" style=\"height: 36px;margin-top: 20px\"/>";
		temp+="</div><div class=\"bardata col-md-4\" style=\"padding-top: 23px;padding-left: 0px\""+yesid+">";
		temp+=yesterday;
		temp+="</div><div class=\"bardata col-md-4\" style=\"padding-top: 23px;padding-left: 0px\""+rateid+">";
		temp+=rate;
		temp+="</div></div><div class=\"three\"><div class=\"col-md-4\"><img src=\"bootstrap/buyone.svg\" style=\"height: 36px;margin-top: 20px\"/></div><div class=\"bardata col-md-5\" style=\"padding-top: 23px;padding-left: 0px\""+buyoneid+">";
		temp+=buyone;
		temp+="</div></div><div class=\"three\"> <div class=\"col-md-4\"><img src=\"bootstrap/predict.svg\" style=\"height: 36px;margin-top: 20px\"/> </div>";
		temp+="<div class=\"bardata col-md-5\" style=\"padding-top: 23px;padding-left: 0px;\""+predictid+">";
		temp+=predict;
		temp+="</div><div class=\"col-md-2\" style=\"padding-left: 0px\""+picid+">";
		temp+="<img src=\"bootstrap/greenarrow.svg\" style=\"height: 18px;margin-top: 27px\"/>";
		temp+="</div></div></div><div class=\"col-md-2\"></div></div>";
		
		return temp;
	}
	
	
}
