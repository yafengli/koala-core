package cn.adv;

import cn.adv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("accountHome")
public class AccountHome extends EntityHome<Account> {

	public void setAccountAid(Long id) {
		setId(id);
	}

	public Long getAccountAid() {
		return (Long) getId();
	}

	@Override
	protected Account createInstance() {
		Account account = new Account();
		return account;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Account getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
