package engine.discreteValues;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Julien Hatin on 12/12/15.
 */
public class LongDiscreteValueTest extends TestCase {

    public void testToDiscreteUniqueValue() throws Exception {
        LongDiscreteValue discreteValue = new LongDiscreteValue(100000000000000000L);
        Assert.assertEquals(discreteValue.toDiscreteUniqueValue(), Long.toString(100000000000000000L));
    }
}