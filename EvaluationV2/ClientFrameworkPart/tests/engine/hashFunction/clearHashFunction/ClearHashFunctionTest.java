package engine.hashFunction.clearHashFunction;

import engine.Association;
import engine.discreteValues.DiscreteValue;
import engine.discreteValues.StringDiscreteValue;
import engine.hashFunction.Hash;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien Hatin on 12/12/15.
 */
public class ClearHashFunctionTest extends TestCase {

    public void testPerformHash() throws Exception {

        List<DiscreteValue> discreteValueList = new ArrayList<>(3);

        discreteValueList.add(new StringDiscreteValue("wifiA"));
        discreteValueList.add(new StringDiscreteValue("wifiB"));
        discreteValueList.add(new StringDiscreteValue("wifiC"));

        List<Association> associationList = new ArrayList<>(1);

        associationList.add(new Association(discreteValueList));
        List<Hash> hash = new ClearHashFunction().performHash(associationList, new DummyKey());
        Assert.assertEquals(hash.get(0).getHash(), "wifiAwifiBwifiC");
    }
}