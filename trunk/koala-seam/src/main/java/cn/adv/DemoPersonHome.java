package cn.adv;

import cn.adv.entity.*;
import cn.adv.interceptor.LoggedInterceptor;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.Interceptors;
import org.jboss.seam.framework.EntityHome;

@Name("demoPersonHome")

public class DemoPersonHome extends EntityHome<DemoPerson> {

	public void setDemoPersonId(Long id) {
		setId(id);
	}

	public Long getDemoPersonId() {
		return (Long) getId();
	}

	@Override    
	protected DemoPerson createInstance() {
		DemoPerson demoPerson = new DemoPerson();
		return demoPerson;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DemoPerson getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
