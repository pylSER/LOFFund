package tool;

public class CodeTranslate {

	public int getNumberOfCode(String codeNumber) {
		String s[] = codeNumber.split(",");
		return s.length;
	}
}
