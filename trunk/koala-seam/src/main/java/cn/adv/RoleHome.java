package cn.adv;

import cn.adv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("roleHome")
public class RoleHome extends EntityHome<Role> {

	public void setRoleRid(Long id) {
		setId(id);
	}

	public Long getRoleRid() {
		return (Long) getId();
	}

	@Override
	protected Role createInstance() {
		Role role = new Role();
		return role;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Role getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
