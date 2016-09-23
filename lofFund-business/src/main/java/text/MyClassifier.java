package text;

import com.aliasi.util.Files;
import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.lm.NGramProcessLM;

import java.io.File;
import java.io.IOException;

public class MyClassifier {
	
	private String filePath="D:\\Competiton\\花旗杯\\lofdata\\POLARITY_DIR\\txt_sentoken";
    //定义变量和初始化
    File mPolarityDir;
    String[] mCategories;
    DynamicLMClassifier<NGramProcessLM> tClassifier;

    MyClassifier() {
        int nGram = 8;
        mPolarityDir = new File(filePath);    //获取语料集
        mCategories = mPolarityDir.list();  //获取类别 
        tClassifier = DynamicLMClassifier.createNGramProcess(mCategories,nGram); //构造分类器
        try {
            train();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //分类器训练
    void train() throws ClassNotFoundException,IOException {
        for (int i = 0; i < mCategories.length; ++i) {
            String category = mCategories[i];
            Classification classification = new Classification(category);
            File file = new File(mPolarityDir,mCategories[i]);
            File[] trainFiles = file.listFiles();
            for (int j = 0; j < trainFiles.length; ++j) {
                File trainFile = trainFiles[j];
                String review = Files.readFromFile(trainFile, "UTF-8");
                Classified<CharSequence> classified = new Classified<CharSequence>(review,classification);
                tClassifier.handle(classified);  
            }
        }        
    }

    //返回分类器
   public DynamicLMClassifier<NGramProcessLM> getClassifier(){
        return tClassifier;
   }
}
