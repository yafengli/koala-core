package cn.adv;

import cn.adv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("accountList")
public class AccountList extends EntityQuery<Account> {

	private static final String EJBQL = "select account from Account account";

	private static final String[] RESTRICTIONS = {
			"lower(account.desc) like concat(lower(#{accountList.account.desc}),'%')",
			"lower(account.loginname) like concat(lower(#{accountList.account.loginname}),'%')",
			"lower(account.password) like concat(lower(#{accountList.account.password}),'%')",};

	private Account account = new Account();

	public AccountList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Account getAccount() {
		return account;
	}
}
