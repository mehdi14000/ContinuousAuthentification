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

public final class Enrollment {

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

	public final void enroll(int index_user , int user) throws NoSuchAlgorithmException{

		List<Hash> list_user = null; 
		List<Hash> list_userMalware = null;
		double currentTime = 0d;
		AssociationEngine _associationEngine = new AssociationEngine(3);
		double SLOT_TIME= 3 * ExtractorMalware.ONE_MINUTE;
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

					list_user = _hashFunctionUser.performHash(associationList, _secretKeyUser);
					list_userMalware = _hashFunctionMalware.performHash(associationList,_secretKeyMalware);

					list_user.addAll(list_userMalware);

					_verifier.enroll(list_user, currentTime); 
				}	

				currentTime += SLOT_TIME;
			}
			index_user++; 
			feat = featureIterator.next();
		}while((feat.getTimestamp() < ExtractorMalware.ONE_WEEK && index_user <(_featuresUser.get(user)).size()-1));


	}

}
