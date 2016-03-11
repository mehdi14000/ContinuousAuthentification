package engine.discreteValues;

/**
 * Created by Julien Hatin on 09/12/15.
 *
 * Create a discrete value from a string
 *
 */
public class StringDiscreteValue extends DiscreteValue {

    private String discreteValue;

    public StringDiscreteValue(String discreteValue) {
        this.discreteValue = discreteValue;
    }

    @Override
    public String toDiscreteUniqueValue() {
        return discreteValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringDiscreteValue that = (StringDiscreteValue) o;

        return !(discreteValue != null ? !discreteValue.equals(that.discreteValue) : that.discreteValue != null);

    }

    @Override
    public int hashCode() {
        return discreteValue != null ? discreteValue.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StringDiscreteValue{" +
                "discreteValue='" + discreteValue + '\'' +
                '}';
    }
}
