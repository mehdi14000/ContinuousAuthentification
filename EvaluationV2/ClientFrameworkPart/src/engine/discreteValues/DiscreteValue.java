package engine.discreteValues;

/**
 * Created by Julien Hatin on 09/12/15.
 */
public abstract class DiscreteValue implements Comparable {

    public abstract String toDiscreteUniqueValue();

    @Override
    public int compareTo(Object o) {
        if (o instanceof DiscreteValue) {
            return this.toDiscreteUniqueValue().compareTo(((DiscreteValue) o).toDiscreteUniqueValue());
        } else {
            return 0;
        }

    }
}
