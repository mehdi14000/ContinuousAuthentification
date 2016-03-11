package MyEvaluation;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

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
	
	public static void printScore(Verification v,String pathfile ){
		
		try {
			System.setOut(new PrintStream(new FileOutputStream(pathfile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringTokenizer delimiter=new StringTokenizer(v.getScoreList().toString(),",");
		while(delimiter.hasMoreTokens()){
			System.out.println(delimiter.nextToken());
		}
		
	}

	public static void printTimeStamp(Verification verifier,String pathfile){
		
		try {
			System.setOut(new PrintStream(new FileOutputStream(pathfile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		StringTokenizer delimiter=new StringTokenizer(verifier.getTimestampList().toString(),",");
		while(delimiter.hasMoreTokens()){
			System.out.println(delimiter.nextToken());
		}
		
		
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, InterruptedException{

		JsonExtractor extractor = JsonExtractor.getInstance();

		HashMap<Integer, List<Feature>> featuresMalware = extractor.extractFeatures(extractorMalware.datacollectionFile,extractorMalware.dataCollectionUsers); // 10 clients dont on récupère les données
		HashMap<Integer, List<Feature>> featuresUser = extractor.extractFeatures(Constants.datacollectionFile, Constants.dataCollectionSubjects); //10 clients dont on récupère les données

		List<Integer> usersMalware = extractorMalware.dataCollectionUsers; //La liste des clients pour le malware 
		List<Integer> users = Constants.dataCollectionSubjects; // la liste des clients pour l'user


		HashFunction hashFunctionUser = new Sha256HashFunction(); 
		HashFunction hashFunctionMalware = new Sha256HashFunction();

		SecretKey secretKeyUser = new Sha256SecretKey("Un secret à ne pas partager"); 
		SecretKey secretKeyMalware = new Sha256SecretKey("Autre secret");

		Verifier verifier = new BasicVerifier(new StaticTemplateUpdater(), 0.08, -0.1);

		Enrollment enrol = new Enrollment(featuresUser , verifier,  hashFunctionUser, 
				hashFunctionMalware , secretKeyUser, secretKeyMalware);
		Verification v = new Verification(featuresUser , verifier,  hashFunctionUser, 
				hashFunctionMalware , secretKeyUser, secretKeyMalware);


		int index=0,index1=0;
		
		while(index != users.size() && index1 != usersMalware.size()){
			int index2=0;

			for(int user:users ){
				enrol.enroll(index2,user);
				index++;
				index1++;
				v.verif(user);
				printScore(v,"./out/vecteur/score.txt");
				printTimeStamp(v,"./out/vecteur/timestamp.txt");

			}
		}

	}
	
}


