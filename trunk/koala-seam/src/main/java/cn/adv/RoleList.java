package cn.adv;

import cn.adv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("roleList")
public class RoleList extends EntityQuery<Role> {

	private static final String EJBQL = "select role from Role role";

	private static final String[] RESTRICTIONS = {
			"lower(role.roledesc) like concat(lower(#{roleList.role.roledesc}),'%')",
			"lower(role.rolename) like concat(lower(#{roleList.role.rolename}),'%')",};

	private Role role = new Role();

	public RoleList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Role getRole() {
		return role;
	}
}
