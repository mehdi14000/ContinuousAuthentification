package template;

import engine.hashFunction.Hash;

import java.util.Map;

/**
 * Created by Julien Hatin on 10/12/15.
 */
public interface Template extends Map<Hash, Integer> {

    int getTotalOfEntries();

    Integer put(Hash Key);

    boolean isReady();

    void setReady();
}
