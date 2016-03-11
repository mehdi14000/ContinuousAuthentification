package templateUpdater;

import engine.hashFunction.Hash;
import template.BasicTemplate;

import java.util.List;

/**
 * Created by Julien Hatin on 10/12/15.
 */
public class StaticTemplateUpdater extends TemplateUpdater {

    private double startTime;
    private boolean first;

    public StaticTemplateUpdater() {
        super(new BasicTemplate());
                this.first = true;
    }

    @Override
    public void enroll(List<Hash> hashList, double timestamp) {
         for (Hash hash : hashList) {
                getTemplate().put(hash);
            }
        }

}
