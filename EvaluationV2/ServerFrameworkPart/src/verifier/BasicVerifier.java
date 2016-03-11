package verifier;

import engine.hashFunction.Hash;
import templateUpdater.TemplateUpdater;
import trustScore.TrustScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Julien Hatin on 10/12/15.
 */
public class BasicVerifier extends Verifier {

    protected double delta;
    protected double bonus;
    protected double malus;

    public BasicVerifier(TemplateUpdater templateUpdater, double bonus, double malus) {
        super(templateUpdater);
        this.delta = 0;
        this.bonus = bonus;
        this.malus = malus;
        trustScore = new TrustScore();
    }

    public BasicVerifier(TemplateUpdater templateUpdater) {
        this(templateUpdater, 0.08, -0.1);
    }

    protected static double getMean(Iterable<Double> doubleList) {
        double sum = 0;
        int nbOfElements = 0;

        for (double d : doubleList) {
            sum += d;
            nbOfElements++;
        }

        if (0 == nbOfElements) {
            return 0;
        } else {
            return sum / (double) nbOfElements;
        }
    }

    @Override
    public void enroll(List<Hash> hashList, double timestamp) {
        templateUpdater.enroll(hashList, timestamp);
    }

    @Override
    public void verify(List<Hash> hashList, double timestamp) {

        if (hashList.size() < 1) {
            return;
        }

        ArrayList<Double> buffer = new ArrayList<>();
        for (Hash hash : hashList) {
            if (templateUpdater.getTemplate().containsKey(hash) && templateUpdater.getTemplate().get(hash) > 1) {
                buffer.add((double) templateUpdater.getTemplate().get(hash) / (double)templateUpdater.getTemplate().getTotalOfEntries());
            } else {
                buffer.add(malus);
            }
        }

        double newDelta = bonus + getMean(buffer);

        if (Math.signum(delta) == Math.signum(newDelta)) {
            delta = delta + newDelta;
        } else {
            delta = newDelta;
        }

        trustScore.add(delta);
    }


}
