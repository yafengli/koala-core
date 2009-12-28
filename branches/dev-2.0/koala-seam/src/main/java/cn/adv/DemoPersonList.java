package cn.adv;

import cn.adv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("demoPersonList")
public class DemoPersonList extends EntityQuery<DemoPerson> {

	private static final String EJBQL = "select demoPerson from DemoPerson demoPerson";

	private static final String[] RESTRICTIONS = {"lower(demoPerson.name) like concat(lower(#{demoPersonList.demoPerson.name}),'%')",};

	private DemoPerson demoPerson = new DemoPerson();

	public DemoPersonList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DemoPerson getDemoPerson() {
		return demoPerson;
	}
}
