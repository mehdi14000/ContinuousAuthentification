package engine.discreteValues;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Julien Hatin on 12/12/15.
 */
public class IntegerDiscreteValueTest extends TestCase {

    public void testToDiscreteUniqueValue() throws Exception {

        IntegerDiscreteValue discreteValue = new IntegerDiscreteValue(5);
        Assert.assertEquals(discreteValue.toDiscreteUniqueValue(), Integer.toString(5));

    }
}