package build_json;

import java.util.Iterator;

import engine.discreteValues.DiscreteValue;

/**
 * Created by Julien Hatin on 09/12/15.
 */
public class Feature {

    private DiscreteValue discreteValue;
    private double timestamp;

    public Feature(double timestamp, DiscreteValue discreteValue) {
        this.discreteValue = discreteValue;
        this.timestamp = timestamp;
    }

    public DiscreteValue getDiscreteValue() {
        return discreteValue;
    }

    public void setDiscreteValue(DiscreteValue discreteValue) {
        this.discreteValue = discreteValue;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature = (Feature) o;

        if (Double.compare(feature.timestamp, timestamp) != 0) return false;
        return !(discreteValue != null ? !discreteValue.equals(feature.discreteValue) : feature.discreteValue != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = discreteValue != null ? discreteValue.hashCode() : 0;
        temp = Double.doubleToLongBits(timestamp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "build_json.Feature{" +
                "discreteValue=" + discreteValue +
                ", timestamp=" + timestamp +
                '}';
    }

}
