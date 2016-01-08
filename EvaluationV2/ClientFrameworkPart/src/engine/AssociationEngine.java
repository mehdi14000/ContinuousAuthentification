package engine;

import engine.discreteValues.DiscreteValue;

import java.util.*;

/**
 * Created by Julien Hatin on 09/12/15.
 * <p/>
 *
 * The AssociationEngine class works like an Arraylist.
 * Who can add Discrete value to it. Once all the discrete value are entered (even duplicated entries),
 * an association is made with the given number of discreteValues
 *
 */
public class AssociationEngine extends ArrayList<DiscreteValue> {

    private int numberOfDiscreteValues;

    /**
     * Create an AssociationEngine Object
     * @param numberOfDiscreteValues the number of discrete values wished in an association
     */
    public AssociationEngine(int numberOfDiscreteValues) {
        this.numberOfDiscreteValues = numberOfDiscreteValues;
    }

    /**
     *
     * Construct all the possible associations for the current list
     *
     * @return The list of possible association for the given parameters
     */
    public List<Association> getEventAssociation() {
        List<Association> eventAssociation = new ArrayList<>();
        Set set = new HashSet();
        set.addAll(this);
        ArrayList<DiscreteValue> distinctList = new ArrayList<>(set);
        Collections.sort(distinctList);
        for (int i = 0; i < distinctList.size() - (numberOfDiscreteValues - 1); i++) {
            for (int j = i; j < distinctList.size() - (numberOfDiscreteValues - 1); j++) {
                List<DiscreteValue> associationList = new ArrayList<>();
                associationList.add(distinctList.get(i));
                for (int k = 1; k < numberOfDiscreteValues; k++) {
                    associationList.add(distinctList.get(j + k));
                }
                eventAssociation.add(new Association(associationList));
            }
        }

        return eventAssociation;
    }

    /**
     *
     * @return the number of discrete value per association
     */
    public int getNumberOfDiscreteValues() {
        return numberOfDiscreteValues;
    }

    /**
     *
     * @param numberOfDiscreteValues the number of discrete value per association
     */
    public void setNumberOfDiscreteValues(int numberOfDiscreteValues) {
        this.numberOfDiscreteValues = numberOfDiscreteValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AssociationEngine that = (AssociationEngine) o;

        return numberOfDiscreteValues == that.numberOfDiscreteValues;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + numberOfDiscreteValues;
        return result;
    }

    @Override
    public String toString() {
        return "AssociationEngine{" + super.toString() + ", " +
                "numberOfDiscreteValues=" + numberOfDiscreteValues +
                '}';
    }
}
