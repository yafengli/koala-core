package cn.adv;

import cn.adv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("userRoleList")
public class UserRoleList extends EntityQuery<UserRole> {

	private static final String EJBQL = "select userRole from UserRole userRole";

	private static final String[] RESTRICTIONS = {};

	private UserRole userRole;

	public UserRoleList() {
		userRole = new UserRole();
		userRole.setPk(new UserRolePK());
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public UserRole getUserRole() {
		return userRole;
	}
}
