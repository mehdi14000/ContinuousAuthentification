package MyEvaluation;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

public final class Writer {


	public void printScore(Verification v,String pathfile ){

		try {
			System.setOut(new PrintStream(new FileOutputStream(pathfile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringTokenizer delimiter=new StringTokenizer(v.getScoreList().toString(),"[,]");
		while(delimiter.hasMoreTokens()){
			System.out.println(delimiter.nextToken());
		}

	}

	public void printTimeStamp(Verification verifier,String pathfile){

		try {
			System.setOut(new PrintStream(new FileOutputStream(pathfile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringTokenizer delimiter=new StringTokenizer(verifier.getTimestampList().toString(),"[,]");
		while(delimiter.hasMoreTokens()){
			System.out.println(delimiter.nextToken());
		}
	}

}
