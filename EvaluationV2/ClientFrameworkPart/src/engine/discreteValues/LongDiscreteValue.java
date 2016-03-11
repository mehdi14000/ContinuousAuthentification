package engine.discreteValues;

/**
 * Created by Julien Hatin on 11/12/15.
 *
 * Create a discrete value from a Long
 *
 */
public class LongDiscreteValue extends DiscreteValue {

    private String discreteUniqueValue;

    public LongDiscreteValue(Long l) {
        discreteUniqueValue = l.toString();
    }

    @Override
    public String toDiscreteUniqueValue() {
        return discreteUniqueValue;
    }
}
