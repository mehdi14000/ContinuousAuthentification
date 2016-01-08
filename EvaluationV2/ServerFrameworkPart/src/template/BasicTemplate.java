package template;

import engine.hashFunction.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Julien Hatin on 10/12/15.
 */
public class BasicTemplate extends HashMap<Hash, Integer> implements Template {

    private int totalNumberOfEntries;
    private boolean isReady;

    public BasicTemplate(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.totalNumberOfEntries = 0;
    }

    public BasicTemplate(int initialCapacity) {
        super(initialCapacity);
        this.totalNumberOfEntries = 0;
    }

    public BasicTemplate() {
        this.totalNumberOfEntries = 0;
    }

    public BasicTemplate(Map<? extends Hash, ? extends Integer> m) {
        super(m);
        this.totalNumberOfEntries = 0;
    }

    public Integer put(Hash key) {
        totalNumberOfEntries++;
        int value = 0;
        if (this.containsKey(key)) {
            value = this.get(key);
        }
        value++;
        return super.put(key, value);
    }


    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void setReady() {
        isReady = true;
    }

    public int getTotalNumberOfEntries() {
        return totalNumberOfEntries;
    }

    public void setTotalNumberOfEntries(int totalNumberOfEntries) {
        this.totalNumberOfEntries = totalNumberOfEntries;
    }

    @Override
    public int getTotalOfEntries() {
        return totalNumberOfEntries;
    }
}
