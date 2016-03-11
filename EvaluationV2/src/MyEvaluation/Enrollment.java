package MyEvaluation;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import build_json.Feature;
import verifier.Verifier;
import engine.Association;
import engine.AssociationEngine;
import engine.hashFunction.Hash;
import engine.hashFunction.HashFunction;
import engine.hashFunction.SecretKey;
import evaluation.utils.Constants;

public class Enrollment {

	private HashMap<Integer, List<Feature>> _featuresUser;
	private Verifier _verifier;
	private HashFunction _hashFunctionUser;
	private SecretKey _secretKeyUser;
	private HashFunction _hashFunctionMalware;
	private SecretKey _secretKeyMalware;
	
	
	public Enrollment(HashMap<Integer, List<Feature>> featuresUser ,Verifier verifier , HashFunction hashFunctionUser, 
						HashFunction hashFunctionMalware ,SecretKey secretKeyUser,SecretKey secretKeyMalware){
		
		_featuresUser = featuresUser;
		_verifier = verifier;	
		 _hashFunctionMalware = hashFunctionMalware;
		 _hashFunctionUser = hashFunctionUser;
		 _secretKeyMalware = secretKeyMalware;
		 _secretKeyUser = secretKeyUser;
		
	}
	
	public void enroll(int index2 , int user) throws NoSuchAlgorithmException{
		
		List<Hash> list1 = null; 
		List<Hash> list2 = null;
		double currentTime = 0d;
		AssociationEngine _associationEngine = new AssociationEngine(3);
		double SLOT_TIME= 3 * extractorMalware.ONE_MINUTE;
		Iterator<Feature> featureIterator=_featuresUser.get(user).iterator();
		Feature feat=featureIterator.next();

		do{
			if ((feat.getTimestamp()< currentTime + SLOT_TIME)) {
				_associationEngine.add(feat.getDiscreteValue());
			} else {
				currentTime = currentTime + SLOT_TIME;
				if (_associationEngine.size() > 3) { 
					List<Association> associationList = _associationEngine.getEventAssociation(); 
					_associationEngine.clear();

					list1 = _hashFunctionUser.performHash(associationList, _secretKeyUser);
					list2 = _hashFunctionMalware.performHash(associationList,_secretKeyMalware);

					list1.addAll(list2);

					_verifier.enroll(list1, currentTime); 
				}	
				
				currentTime += SLOT_TIME;
			}
			index2++; 
			feat = featureIterator.next();
		}while((feat.getTimestamp() < extractorMalware.ONE_WEEK && index2 <(_featuresUser.get(user)).size()-1));
		
	
	}

}
