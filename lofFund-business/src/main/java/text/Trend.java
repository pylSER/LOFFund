package text;

import java.util.ArrayList;

import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.JointClassification;
import com.aliasi.lm.NGramProcessLM;

import blService.TrendService;
import serialization.TextGetter;

public class Trend implements TrendService {

	/**
	 * 
	 * @return 返回乐观、中立、悲观的占比,[乐观,中立,悲观]
	 */
	public double[] getTrend() {
		MyClassifier classfy = new MyClassifier();
		DynamicLMClassifier<NGramProcessLM> c = classfy.getClassifier();
		TextGetter textGetter = new TextGetter();
		ArrayList<String> list = textGetter.getText();
		JointClassification j = null;
		String cla = null;
		double[] category = { 0, 0, 0 };
		for (String text : list) {
			j = c.classify(text);
			cla = j.bestCategory();
			if(cla.equals("pos"))
				category[0]++;
			else if(cla.equals("neg"))
				category[1]++;
			else
				category[2]++;
		}
		category[0] /= list.size();
		category[1] /= list.size();
		category[2] /= list.size();

		return category;
	}
}
