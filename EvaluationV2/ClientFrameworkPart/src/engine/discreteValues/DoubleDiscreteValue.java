package engine.discreteValues;

/**
 * Created by Julien Hatin on 11/12/15.
 */
public class DoubleDiscreteValue extends DiscreteValue {

    private String discreteUniqueValue;

    public DoubleDiscreteValue(Double d) {
        discreteUniqueValue = Double.toString(d);
    }

    @Override
    public String toDiscreteUniqueValue() {
        return discreteUniqueValue;
    }
}
