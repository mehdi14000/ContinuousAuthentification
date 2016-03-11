package templateUpdater;

import engine.hashFunction.Hash;
import template.BasicTemplate;
import template.Template;

import java.util.List;

/**
 * Created by Julien Hatin on 13/12/15.
 */
public class DynamicTemplateUpdater extends TemplateUpdater {

    public DynamicTemplateUpdater(Template template) {
        super(template);
    }

    public DynamicTemplateUpdater() {
        setTemplate(new BasicTemplate());
    }

    @Override
    public void enroll(List<Hash> hashList, double timestamp) {
        for (Hash hash : hashList) {
            getTemplate().put(hash);
        }

        getTemplate().setReady();
    }
}
