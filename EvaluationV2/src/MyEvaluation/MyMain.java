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

	
	public static List<Hash> enrollementUser(List<Feature> feats,HashFunction hashFunction,SecretKey secretKey) throws NoSuchAlgorithmException{
	
		 List<Hash> hashList=null;
		 double SLOT_TIME = 3 * extractorClient.ONE_MINUTE; //On echantillone toute les 3 minutes
		 double currentTime = 0d; //Le temps de départ est fixé à zéro
		 
		 AssociationEngine associationEngine = new AssociationEngine(3); // On associe les événements par 3
		
		 Iterator<Feature> featureIterator=feats.iterator();
		 Feature feat = featureIterator.next(); 
		 do{
			 while (feat.getTimestamp() < extractorClient.ONE_WEEK) {
				 if ((feat.getTimestamp() < currentTime + SLOT_TIME)) {
					 associationEngine.add(feat.getDiscreteValue());
				 } else {
					 currentTime = currentTime + SLOT_TIME;
					 if (associationEngine.size() > 3) { // Si on a plus de 3 events
						 List<Association> associationList = associationEngine.getEventAssociation(); //On récupère les associations
						 associationEngine.clear(); //On oublie pas de vider la liste des valuers

						 hashList = hashFunction.performHash(associationList, secretKey); // On récupère la liste des hash

					 }
					 currentTime += SLOT_TIME; // On avance de 3 minutes
				 }
				 feat=featureIterator.next();
		 	}
			feat=featureIterator.next();
		 }while(featureIterator.hasNext());
		 
		 return hashList;	
	}
	
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException{
	
		 JsonExtractor extractor = JsonExtractor.getInstance();
		 
	     HashMap<Integer, List<Feature>> featuresMalware = extractor.extractFeatures(extractorClient.datacollectionFile,extractorClient.dataCollectionUsers); // 10 clients dont on récupère les données
	     HashMap<Integer, List<Feature>> featuresUser = extractor.extractFeatures(Constants.datacollectionFile, Constants.dataCollectionSubjects); //10 clients dont on récupère les données
	     
	     
	     List<Integer> users = extractorClient.dataCollectionUsers; //La liste des clients dans le fichier de données
    	 List<Double> scoreList = new ArrayList();
    	 List<Double> timestampList = new ArrayList();
    	 
	     HashFunction hashFunction = new Sha256HashFunction(); 
	     SecretKey secretKey = new Sha256SecretKey("Un secret à ne pas partager"); 
	     
	     HashFunction hashGenerate = new Sha256HashFunction();
	     SecretKey secretKey1 = new Sha256SecretKey("Autre secret");
	     
	     double SLOT_TIME = 3 * extractorClient.ONE_MINUTE;
	     Verifier verifier = new BasicVerifier(new StaticTemplateUpdater(), 0.08, -0.1);
	     AssociationEngine associationEngine = new AssociationEngine(3);

    	 
	     for(int user : users){ 
	    	 
	    	 Iterator<Feature> featureIt = (featuresMalware.get(user)).iterator();
	    	 
	    	 List<Hash> list1=null,list2=null;
	    	 list1=enrollementUser(featuresUser.get(user),hashFunction,secretKey);
	    	 list2=enrollementUser(featuresMalware.get(user),hashGenerate,secretKey1);
	    	 list1.addAll(list2); // Concaténation des deux listes et on le met dans la liste normale
	    	 
	    	 verifier.enroll(list1, 0d); 
	 
	    	 double currentTime = 0d; 
	    	 
	    	 for(int j=0;j<featuresUser.size();j++){
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
	    					 List<Hash> hashList = hashFunction.performHash(associationList, secretKey); 
	    					 verifier.verify(hashList, currentTime); 
	    				 }
	    		
	    				 scoreList.add(verifier.getTrustScore().getScore());
	    				 timestampList.add(currentTime);
	    				 currentTime += SLOT_TIME; 
	    			 }
	    	         
	    			feat= featureIterator.next();
	    		 }while(featureIterator.hasNext());
	    	 }
	     }
	     
         System.out.println(timestampList.toString()); 
         System.out.println("\nScore de l'utilisateur\n"+scoreList.toString());
	     
	}

}
