package engine.hashFunction.sha256;

import engine.Association;
import engine.hashFunction.Hash;
import engine.hashFunction.HashFunction;
import engine.hashFunction.SecretKey;
import utils.BytesOperations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Julien Hatin on 09/12/15.
 */
public class Sha256HashFunction extends HashFunction {

    @Override
    protected Hash performHash(Association association, SecretKey key) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return new Hash(BytesOperations.convert(digest.digest((association.getAssociation() + key.toString()).getBytes())));
    }
}
