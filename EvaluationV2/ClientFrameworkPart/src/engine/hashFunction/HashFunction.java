package engine.hashFunction;

import engine.Association;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien Hatin on 09/12/15.
 */
public abstract class HashFunction {

    public List<Hash> performHash(List<Association> associations, SecretKey key) throws NoSuchAlgorithmException {
        List<Hash> hashList = new ArrayList<>();
        for (Association association : associations) {
            hashList.add(performHash(association, key));
        }
        return hashList;
    }

    protected abstract Hash performHash(Association association, SecretKey key) throws NoSuchAlgorithmException;

}
