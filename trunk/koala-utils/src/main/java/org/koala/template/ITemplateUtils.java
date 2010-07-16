package org.koala.template;

import java.util.Map;

/**
 * User: phoenixup
 * Date: 2010-7-16
 * Time: 10:39:43
 */
public interface ITemplateUtils {
    /**
     * @param templateFileName
     * @param model
     * @return
     */
    public String template(String templateFileName, Map model);

    /**
     * @param initObj
     */
    public void init(Object initObj);


}
