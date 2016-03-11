package engine.discreteValues;

import junit.framework.TestCase;

/**
 * Created by Julien Hatin on 12/12/15.
 *
 * Create a discrete value from a double
 *
 */
public class DoubleDiscreteValueTest extends TestCase {

    public void testToDiscreteUniqueValue() throws Exception {
        DoubleDiscreteValue discreteValue = new DoubleDiscreteValue(2.0);

        String stringValue = discreteValue.toDiscreteUniqueValue();

        assertEquals(stringValue, Double.toString(2.0));
    }
}