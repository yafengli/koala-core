package org.koala.utils.template;

/**
 * User: phoenixup
 * Date: 2010-7-16
 * Time: 10:39:43
 */
public interface ITemplateUtilsFactory {

	public ITemplateUtils factory(Class c);

	public ITemplateUtils factory(String utilsClassName);
}