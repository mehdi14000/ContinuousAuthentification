package verifier;

import engine.hashFunction.Hash;
import templateUpdater.TemplateUpdater;
import trustScore.TrustScore;

import java.util.Date;
import java.util.List;

/**
 * Created by Julien Hatin on 10/12/15.
 */
public abstract class Verifier {

    protected TemplateUpdater templateUpdater;
    protected TrustScore trustScore;

    public Verifier(TemplateUpdater templateUpdater, TrustScore trustScore) {
        this.templateUpdater = templateUpdater;
        this.trustScore = trustScore;
    }

    public Verifier(TemplateUpdater templateUpdater) {
        this.templateUpdater = templateUpdater;
    }

    public abstract void verify(List<Hash> hashList, double timestamp);
    public abstract void enroll(List<Hash> hashList, double timestamp);

    public TrustScore getTrustScore() {
        return trustScore;
    }

}
