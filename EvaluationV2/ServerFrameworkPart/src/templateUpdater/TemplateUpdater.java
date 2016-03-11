package templateUpdater;

import engine.hashFunction.Hash;
import template.Template;

import java.util.List;

/**
 * Created by Julien Hatin on 10/12/15.
 */
public abstract class TemplateUpdater {

    private Template template;

    public TemplateUpdater() {
    }

    public TemplateUpdater(Template template) {
        this.template = template;
    }

    public abstract void enroll(List<Hash> hashList, double timestamp);

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemplateUpdater that = (TemplateUpdater) o;

        return !(template != null ? !template.equals(that.template) : that.template != null);
    }

    @Override
    public int hashCode() {
        return template != null ? template.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TemplateUpdater{" +
                "template=" + template +
                '}';
    }
}