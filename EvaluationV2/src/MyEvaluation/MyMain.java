package MyEvaluation;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

import templateUpdater.StaticTemplateUpdater;
import verifier.BasicVerifier;
import verifier.Verifier;
import engine.Association;
import engine.AssociationEngine;
import engine.hashFunction.Hash;
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

		HashMap<Integer, List<Feature>> featuresMalware = extractor.extractFeatures(extractorMalware.datacollectionFile,extractorMalware.dataCollectionUsers); // 10 clients dont on récupère les données
		HashMap<Integer, List<Feature>> featuresUser = extractor.extractFeatures(Constants.datacollectionFile, Constants.dataCollectionSubjects); //10 clients dont on récupère les données

		List<Integer> usersMalware = extractorMalware.dataCollectionUsers; //La liste des clients pour le malware 
		List<Integer> users = Constants.dataCollectionSubjects; // la liste des clients pour l'user

		List<Double> scoreList = new ArrayList();
		List<Double> timestampList = new ArrayList();
		List<Hash> list1 = null; 
		List<Hash> list2 = null; 
		List<Hash> list3 = null, list4 = null;


		HashFunction hashFunctionUser = new Sha256HashFunction(); 
		HashFunction hashFunctionMalware = new Sha256HashFunction();

		SecretKey secretKeyUser = new Sha256SecretKey("Un secret à ne pas partager"); 
		SecretKey secretKeyMalware = new Sha256SecretKey("Autre secret");

		Verifier verifier = new BasicVerifier(new StaticTemplateUpdater(), 0.08, -0.1);
		AssociationEngine associationEngine = new AssociationEngine(3);
		//Enrollement e = new Enrollement();

		double SLOT_TIME = 3 * extractorMalware.ONE_MINUTE;
		double currentTime = 0d;
		int index=0,index1=0;


		while(index != users.size() && index1 != usersMalware.size()){


			int index2=0;


			for(int user:users ){

				Iterator<Feature> featureIterator=featuresUser.get(user).iterator();
				Feature feat=featureIterator.next();

				do{
					if ((feat.getTimestamp()< currentTime + SLOT_TIME)) {
						associationEngine.add(feat.getDiscreteValue());
					} else {
						currentTime = currentTime + SLOT_TIME;
						if (associationEngine.size() > 3) { 
							List<Association> associationList = associationEngine.getEventAssociation(); 
							associationEngine.clear();

							list1 = hashFunctionUser.performHash(associationList, secretKeyUser);
							list2 = hashFunctionMalware.performHash(associationList,secretKeyMalware);

							list1.addAll(list2);

							verifier.enroll(list1, currentTime); 
						}	
						currentTime += SLOT_TIME;
					}
					index2++; 
					feat = featureIterator.next();
				}while((feat.getTimestamp() < extractorMalware.ONE_WEEK && index2 <(featuresUser.get(user)).size()-1));

				index++;
				index1++;


				do{
					if ((feat.getTimestamp()< currentTime + SLOT_TIME)) {
						associationEngine.add(feat.getDiscreteValue());
					} else {
						currentTime = currentTime + SLOT_TIME;
						if (associationEngine.size() > 3) { 
							List<Association> associationList = associationEngine.getEventAssociation(); 
							associationEngine.clear();

							list3 = hashFunctionUser.performHash(associationList, secretKeyUser);
							list4 = hashFunctionMalware.performHash(associationList,secretKeyMalware);

							list3.addAll(list4);

							verifier.verify(list3, currentTime); 
						}
						// System.out.println("verification  :" +currentTime);
						scoreList.add(verifier.getTrustScore().getScore());
						timestampList.add(currentTime);
						currentTime += SLOT_TIME; 
					}

					feat= featureIterator.next();
				}while(featureIterator.hasNext()); 

				System.out.println("list3 : "+ list3);
				//System.setOut(new PrintStream(new FileOutputStream("out.log")));	 
				// System.out.println("utilisateur : "+ user);
				//System.out.println(timestampList.toString()); 
				System.out.println("\nScore de l'utilisateur\n"+scoreList.toString());
			}
		}
	}

}


