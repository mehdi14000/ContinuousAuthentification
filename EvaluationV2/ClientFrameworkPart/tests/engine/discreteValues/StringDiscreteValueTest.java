package engine.discreteValues;

import junit.framework.TestCase;

/**
 * Created by Julien Hatin on 12/12/15.
 */
public class StringDiscreteValueTest extends TestCase {

    public void testToDiscreteUniqueValue() throws Exception {
        StringDiscreteValue discreteValue = new StringDiscreteValue("wifiA");
        assertEquals(discreteValue.toDiscreteUniqueValue(), "wifiA");
    }
}