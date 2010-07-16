package org.koala.template;

/**
 * User: phoenixup
 * Date: 2010-7-16
 * Time: 10:39:43
 */
public class TemplateUtilsFactory implements ITemplateUtilsFactory {


    private ITemplateUtils utils;


    public ITemplateUtils getUtils() {
        return utils;
    }

    public void setUtils(ITemplateUtils utils) {
        this.utils = utils;
    }

    public ITemplateUtils factory() {
        ITemplateUtils tu = null;
        if (this.getUtils() != null) {
            tu = this.getUtils();
        }
        return tu;
    }

    public ITemplateUtils factory(String utilsClassName) {
        ITemplateUtils tu = null;
        try {
            if (utilsClassName != null) {
                tu = (ITemplateUtils) Class.forName(utilsClassName).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tu;
    }
}