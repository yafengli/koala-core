package cn.adv.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adv_userrole")
public class UserRole implements Serializable {

	//seam-gen attributes (you should probably edit these)
	private UserRolePK pk;
	private Account account;
	private Role role;

	//seam-gen attribute getters/setters with annotations (you probably should edit)

	@EmbeddedId
	public UserRolePK getPk() {
		return pk;
	}

	public void setPk(UserRolePK pk) {
		this.pk = pk;
	}

//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "aid", referencedColumnName = "aid", nullable = false, insertable = false, updatable = false)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "rid", referencedColumnName = "rid", nullable = false, insertable = false, updatable = false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
