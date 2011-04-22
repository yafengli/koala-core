package test.model;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "find.username", query = "select eu from ExUser eu where eu.username=:username"),
		@NamedQuery(name = "find.exkey", query = "select eu from ExUser eu where eu.exkey=:exkey") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "native.find.username", query = "select * from TEST_I_USER where username=?", resultClass = ExUser.class),
		@NamedNativeQuery(name = "native.find.exkey", query = "select * from TEST_I_USER where exkey=?", resultClass = ExUser.class) })
public class ExUser extends IUser {
	@Basic
	@Column(name = "exkey", length = 50, nullable = false)
	protected String exkey;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "udid", referencedColumnName = "id", unique = true, nullable = false)
	private UserDetail userDetail;

	public String getExkey() {
		return exkey;
	}

	public void setExkey(String exkey) {
		this.exkey = exkey;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
}
