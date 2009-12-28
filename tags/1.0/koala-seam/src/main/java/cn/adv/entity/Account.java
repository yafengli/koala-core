package cn.adv.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.Length;

@Entity
@Table(name = "adv_account")
public class Account implements Serializable {

	//seam-gen attributes (you should probably edit these)
	private Long aid;
	private String loginname;
    private String password;
	private String desc;

	//add additional entity attributes

	//seam-gen attribute getters/setters with annotations (you probably should edit)

	@Id
	@GeneratedValue
	@Column(name = "aid")
	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	@Length(min = 5, max = 30)
    @Column(name = "loginname",unique = true)
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
    @Length(min = 5,max = 20)
    @Column(name = "password",length = 20,nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Length(min = 5, max = 5000)
	@Column(name = "description")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
