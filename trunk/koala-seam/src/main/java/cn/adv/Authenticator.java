package cn.adv;

import cn.adv.entity.Account;
import cn.adv.entity.Role;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Name("authenticator")
public class Authenticator {
	@Logger
	Log log;

	@In
	Identity identity;

	@In
	FacesMessages facesMessages;

	@In
	EntityManager entityManager;

	public boolean authenticate() {
		try {
			Account acc = loadUser();
			if (acc != null && identity.getPassword().equals(acc.getPassword())) {
				for (Role role : loadRoles(acc)) {
					identity.addRole(role.getRolename());
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			facesMessages.addFromResourceBundle("validator.login.failed");
		}
		return false;

	}

	private Account loadUser() {
		try {
			log.fatal("authenticating [#0]", identity.getUsername());
			Query query = entityManager
					.createQuery("SELECT acc from Account acc WHERE acc.loginname =?");
			query.setParameter(1, identity.getUsername().trim());
			Object obj = query.getSingleResult();
			if (obj != null && obj instanceof Account)
				return (Account) obj;
		} catch (Exception e) {
			facesMessages.addFromResourceBundle("validator.login.nouser");
		}
		return null;
	}

	private List<Role> loadRoles(Account acc) {
		Query query = entityManager
				.createQuery("SELECT ur.role from UserRole ur WHERE ur.account =?");
		query.setParameter(1, acc);
		return query.getResultList();
	}
}
