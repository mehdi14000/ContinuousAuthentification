package MyEvaluation;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import verifier.Verifier;
import build_json.Feature;
import engine.Association;
import engine.AssociationEngine;
import engine.hashFunction.Hash;
import engine.hashFunction.HashFunction;
import engine.hashFunction.SecretKey;


public class Verification {

	private HashMap<Integer, List<Feature>> _featuresUser;
	private Verifier _verifier;
	private HashFunction _hashFunctionUser;
	private SecretKey _secretKeyUser;
	private HashFunction _hashFunctionMalware;
	private SecretKey _secretKeyMalware;
	private List<Double> _timestampList;
	private List<Double> _scoreList;
	
	public Verification(HashMap<Integer, List<Feature>> featuresUser ,Verifier verifier , HashFunction hashFunctionUser, 
						HashFunction hashFunctionMalware ,SecretKey secretKeyUser,SecretKey secretKeyMalware){
		

		_featuresUser = featuresUser;
		_verifier = verifier;
		 _hashFunctionMalware = hashFunctionMalware;
		 _hashFunctionUser = hashFunctionUser;
		 _secretKeyMalware = secretKeyMalware;
		 _secretKeyUser = secretKeyUser;
		 _scoreList = new ArrayList<Double>();
		_timestampList = new ArrayList<Double>(); 

		
	}
	
	public void verif(int user) throws NoSuchAlgorithmException{
		
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

					_verifier.verify(list1, currentTime); 
				}
			
				_scoreList.add(_verifier.getTrustScore().getScore());
				_timestampList.add(currentTime);
				currentTime += SLOT_TIME; 
			}

			feat= featureIterator.next();
		}while(featureIterator.hasNext());
	
		
	}

	public List<Double> getTimestampList() {
		return _timestampList;
	}


	public List<Double> getScoreList() {
		return _scoreList;
	}
	
}
