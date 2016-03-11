package trustScore;

/**
 * Created by Julien Hatin on 11/12/15.
 */
public class TrustScore {

    protected double score;

    public TrustScore() {
        score = 0;
    }

    public TrustScore add(double delta) {
        score += delta;
        if (score > 1) {
            score = 1;
        } else if (score < 0) {
            score = 0;
        }
        return this;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrustScore that = (TrustScore) o;

        return Double.compare(that.score, score) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(score);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "TrustScore{" +
                "score=" + score +
                '}';
    }
}
