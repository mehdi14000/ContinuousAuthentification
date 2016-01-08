package engine.hashFunction.clearHashFunction;

import engine.Association;
import engine.hashFunction.Hash;
import engine.hashFunction.HashFunction;
import engine.hashFunction.SecretKey;


/**
 * Created by Julien Hatin on 09/12/15.
 *
 * This hash function should be used only for test purpose.
 * It does not change the association and keep it in clear.
 * Usefull for debugging.
 *
 */
@Deprecated
public class ClearHashFunction extends HashFunction {

    @Override
    protected Hash performHash(Association association, SecretKey key) {
        return new Hash(association.getAssociation());
    }
}
