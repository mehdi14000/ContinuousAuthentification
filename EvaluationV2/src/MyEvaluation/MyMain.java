package MyEvaluation;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

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
	
		 List<Hash> hashList = null;
		 double SLOT_TIME = 3 * Constants.ONE_MINUTE; //On echantillone toute les 3 minutes
		  
		 AssociationEngine associationEngine = new AssociationEngine(3); // On associe les événements par 3
		 double currentTime = 0d; //Le temps de départ est fixé à zéro

		 for(int i=0;i<feats.size();i++){
			 Feature feat = feats.get(i);
			 while (feat.getTimestamp() < Constants.ONE_WEEK) {
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
		 	}
		 }
		 return hashList;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException{
	
		 JsonExtractor extractor = JsonExtractor.getInstance();
		 
	     HashMap<Integer, List<Feature>> features = extractor.extractFeatures(extractorClient.datacollectionFile,extractorClient.dataCollectionUsers); 
	     HashMap<Integer, List<Feature>> features2 = extractor.extractFeatures(Constants.datacollectionFile, Constants.dataCollectionSubjects); //On récupère les données des clients pour la base de donnée

	     HashFunction hashFunction = new Sha256HashFunction(); 
	     SecretKey secretKey = new Sha256SecretKey("Un secret à ne pas partager"); 
	     
	     HashFunction hashGenerate = new Sha256HashFunction();
	     SecretKey secretKey1 = new Sha256SecretKey("Autre secret");
	     
	     double SLOT_TIME = 3 * Constants.ONE_MINUTE; //On echantillone toutes les 3 minutes
	     
	     for(int i=0;i<features.size();i++){
	    	 
	    	 List<Hash> list1,list2;
	    	 
	    	 list1=enrollementUser(features2.get(i),hashFunction,secretKey);
	    	 list2=enrollementUser(features.get(i),hashGenerate,secretKey1);
	    	 
	     }
	     
	     
	}

}
