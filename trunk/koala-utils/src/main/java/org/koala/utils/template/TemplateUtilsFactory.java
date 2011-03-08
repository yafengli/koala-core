package org.koala.utils.template;

/**
 * User: phoenixup
 * Date: 2010-7-16
 * Time: 10:39:43
 */
public class TemplateUtilsFactory implements ITemplateUtilsFactory {

	@Override
	public ITemplateUtils factory(String utilsClassName) {
		try {
			return factory(Class.forName(utilsClassName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ITemplateUtils factory(Class c) {
		try {
			if (c != null) {
				return (ITemplateUtils) c.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}