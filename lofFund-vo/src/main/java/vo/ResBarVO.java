package vo;

public class ResBarVO {
private String name;
private String code;
private String yesterday;
private String rate;
private double buyone;
private String predict;

public ResBarVO(String name, String code, String yesterday, String rate, double buyone, String predict) {
	super();
	this.name = name;
	this.code = code;
	this.yesterday = yesterday;
	this.rate = rate;
	this.buyone = buyone;
	this.predict = predict;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getYesterday() {
	return yesterday;
}
public void setYesterday(String yesterday) {
	this.yesterday = yesterday;
}
public String getRate() {
	return rate;
}
public void setRate(String rate) {
	this.rate = rate;
}
public double getBuyone() {
	return buyone;
}
public void setBuyone(double buyone) {
	this.buyone = buyone;
}
public String getPredict() {
	return predict;
}
public void setPredict(String predict) {
	this.predict = predict;
}
}
