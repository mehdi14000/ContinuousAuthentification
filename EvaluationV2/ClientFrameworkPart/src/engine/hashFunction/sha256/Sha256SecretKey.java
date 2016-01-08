package engine.hashFunction.sha256;

import engine.hashFunction.SecretKey;

/**
 * Created by Julien Hatin on 09/12/15.
 */
public class Sha256SecretKey implements SecretKey {

    private String key;

    public Sha256SecretKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
