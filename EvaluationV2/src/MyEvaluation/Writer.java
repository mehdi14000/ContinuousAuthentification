package MyEvaluation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

public final class Writer {

	public final void printScore(Verification verification,String pathfile ){

		try {
			System.setOut(new PrintStream(new FileOutputStream(pathfile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringTokenizer delimiter=new StringTokenizer(verification.getScoreList().toString(),"[,]");
		while(delimiter.hasMoreTokens()){
			System.out.println(delimiter.nextToken());
		}

	}

	public final void printTimeStamp(Verification verification,String pathfile){

		try {
			System.setOut(new PrintStream(new FileOutputStream(pathfile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringTokenizer delimiter=new StringTokenizer(verification.getTimestampList().toString(),"[,]");
		while(delimiter.hasMoreTokens()){
			System.out.println(delimiter.nextToken());
		}
	}

}
