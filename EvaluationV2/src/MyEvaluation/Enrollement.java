package MyEvaluation;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import build_json.Feature;
import engine.Association;
import engine.AssociationEngine;
import engine.hashFunction.Hash;
import engine.hashFunction.HashFunction;
import engine.hashFunction.SecretKey;

public class Enrollement {
	private static  List<Association> _associationList = null;
	
	public Enrollement(){
		
	}
	
	public static List<Hash> enrollement(List<Hash> hashList,List<Feature> feats,HashFunction hashFunction,SecretKey secretKey) throws NoSuchAlgorithmException, InterruptedException{
		
		 double SLOT_TIME = 3 * extractorMalware.ONE_MINUTE; //On echantillone toute les 3 minutes
		 double currentTime = 0d; //Le temps de départ est fixé à zéro
		 int index=0;
		 
		 AssociationEngine associationEngine = new AssociationEngine(3); // On associe les événements par 3

		 do{
			 if (((feats.get(index)).getTimestamp() < currentTime + SLOT_TIME)) {
				 associationEngine.add((feats.get(index)).getDiscreteValue());
			 } else {
				 currentTime = currentTime + SLOT_TIME;
				 if (associationEngine.size() > 3) { // Si on a plus de 3 events
					 _associationList = associationEngine.getEventAssociation(); //On récupère les associations
					 associationEngine.clear(); //On oublie pas de vider la liste des valuers

					 hashList = hashFunction.performHash(_associationList, secretKey); // On récupère la liste des hash

				 }
				 currentTime += SLOT_TIME; // On avance de 3 minutes
			 }
			index++;
		 }while((feats.get(index)).getTimestamp() < extractorMalware.ONE_WEEK && index <feats.size()-1);
		 return hashList;	
	}
	
}
