package org.koala.utils.template;

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
	 * @param t
	 * @param <T>
	 */
	public <T> void init(T t);
}
