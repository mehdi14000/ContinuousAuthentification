package engine.hashFunction;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Julien Hatin on 12/12/15.
 */
public class HashTest extends TestCase {

    private Hash hash;
    private static final String testString = "thisisadummystring";

    public void setUp() throws Exception {
        super.setUp();
        hash = new Hash(testString);
    }

    public void testGetHash() throws Exception {
        Assert.assertEquals(hash.getHash(), testString);
    }

    public void testSetHash() throws Exception {
        String otherString = "other string";
        hash.setHash(otherString);
        Assert.assertEquals(hash.getHash(), otherString);
    }
}