package cn.adv;

import cn.adv.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("userRoleHome")
public class UserRoleHome extends EntityHome<UserRole> {

	@In(create = true)
	AccountHome accountHome;
	@In(create = true)
	RoleHome roleHome;

	public void setUserRolePk(UserRolePK id) {
		setId(id);
	}

	public UserRolePK getUserRolePk() {
		return (UserRolePK) getId();
	}

	public UserRoleHome() {
		setUserRolePk(new UserRolePK());
	}

	@Override
	public boolean isIdDefined() {
		if (getUserRolePk().getAid() == null)
			return false;
		if (getUserRolePk().getRid() == null)
			return false;
		return true;
	}

	@Override
	protected UserRole createInstance() {
		UserRole userRole = new UserRole();
		userRole.setPk(new UserRolePK());
		return userRole;
	}

	public void wire() {
		getInstance();
		Account account = accountHome.getDefinedInstance();
		if (account != null) {
			getInstance().setAccount(account);
		}
		Role role = roleHome.getDefinedInstance();
		if (role != null) {
			getInstance().setRole(role);
		}
	}

	public boolean isWired() {
		if (getInstance().getAccount() == null)
			return false;
		if (getInstance().getRole() == null)
			return false;
		return true;
	}

	public UserRole getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
