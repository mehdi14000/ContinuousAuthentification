package engine;

import engine.discreteValues.DiscreteValue;

import java.util.Collections;
import java.util.List;

/**
 * Created by Julien Hatin on 09/12/15.
 *
 * Represent an association of n elements
 *
 */
public class Association {

    private String association;

    /**
     * Contruct an association object from a list of discreteValues
     * @param discreteValueList the list of disrecte value to be associated
     */
    public Association(List<DiscreteValue> discreteValueList) {
        association = "";
        Collections.sort(discreteValueList);
        for (DiscreteValue value : discreteValueList) {
            association += value.toDiscreteUniqueValue();
        }
    }

    /**
     * Return the association as a string
     * @return the association
     */
    public String getAssociation() {
        return association;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Association that = (Association) o;

        return !(association != null ? !association.equals(that.association) : that.association != null);

    }

    @Override
    public int hashCode() {
        return association != null ? association.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Association{" +
                "association='" + association + '\'' +
                '}';
    }
}
