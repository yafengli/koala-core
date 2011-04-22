package cn.hpt.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ht_role")
public class Role {// 角色表

	@Id()
	@SequenceGenerator(name = "ht_role_seq", sequenceName = "ht_roley_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ht_role_seq")
	private Long roleid;

	@Column(unique = true, nullable = false)
	private String rolename;

	private String roledesc;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<Operator> operators;

	public static final String ADMIN_ROLE = "admin";
	public static final String OPERATOR_ROLE = "operator";
	public static final Long ADMIN_ID = 0L;
	public static final Long OPERATOR_ID = -1L;


	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<Operator> getOperators() {
		return operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

}