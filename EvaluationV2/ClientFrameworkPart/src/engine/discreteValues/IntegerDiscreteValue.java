package engine.discreteValues;

/**
 * Created by Julien Hatin on 11/12/15.
 *
 * Create a discrete value from an integer
 *
 */
public class IntegerDiscreteValue extends DiscreteValue {

    private String discreteValue;

    public IntegerDiscreteValue(Integer i) {
        discreteValue = i.toString();
    }

    @Override
    public String toDiscreteUniqueValue() {
        return discreteValue;
    }
}
