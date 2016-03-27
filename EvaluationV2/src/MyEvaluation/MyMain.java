package MyEvaluation;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

import org.jfree.ui.RefineryUtilities;


import templateUpdater.StaticTemplateUpdater;
import verifier.BasicVerifier;
import verifier.Verifier;
import engine.hashFunction.HashFunction;
import engine.hashFunction.SecretKey;
import engine.hashFunction.sha256.Sha256HashFunction;
import engine.hashFunction.sha256.Sha256SecretKey;
import evaluation.utils.Constants;
import build_json.Feature;
import build_json.JsonExtractor;

public final class MyMain {


	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, InterruptedException{

		JsonExtractor extractor = JsonExtractor.getInstance();

		HashMap<Integer, List<Feature>> featuresMalware = extractor.extractFeatures(ExtractorMalware.datacollectionFile,ExtractorMalware.dataCollectionUsers); // 10 clients dont on récupère les données
		HashMap<Integer, List<Feature>> featuresUser = extractor.extractFeatures(Constants.datacollectionFile, Constants.dataCollectionSubjects); //10 clients dont on récupère les données

		List<Integer> usersMalware = ExtractorMalware.dataCollectionUsers; 
		List<Integer> users = Constants.dataCollectionSubjects; 

		HashFunction hashFunctionUser = new Sha256HashFunction(); 
		HashFunction hashFunctionMalware = new Sha256HashFunction();

		SecretKey secretKeyUser = new Sha256SecretKey("Un secret a� ne pas partager"); 
		SecretKey secretKeyMalware = new Sha256SecretKey("Autre secret");

		Verifier verifier = new BasicVerifier(new StaticTemplateUpdater(), 0.08, -0.1);

		Enrollment enroler = new Enrollment(featuresUser , verifier,  hashFunctionUser,hashFunctionMalware , secretKeyUser, secretKeyMalware);
		Verification verification = new Verification(featuresUser , verifier,  hashFunctionUser,hashFunctionMalware , secretKeyUser, secretKeyMalware);
		Writer writer = new Writer();

		int index_user=0,index_userMalware=0;

		/*while(index_user != users.size() && index_userMalware != usersMalware.size()){
			int index_temp=0;

			for(int user:users ){
				enroler.enroll(index_temp,user);
				index_user++;
				index_userMalware++;
				verification.verif(user);
				writer.printScore(v,"./out/vecteur/score.csv");
				writer.printTimeStamp(v,"./out/vecteur/timestamp.csv");
			}
		}*/
	
		Printer printer=new Printer("Vecteur de score");
		printer.pack();
		RefineryUtilities.centerFrameOnScreen(printer);
		printer.setVisible(true);
	}
		
}


