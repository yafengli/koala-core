package cn.adv.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-8-19
 * Time: 16:50:03
 * To change this template use File | Settings | File Templates.
 */
@Embeddable()
public class UserRolePK implements Serializable {

	private Long aid;
	private Long rid;

	@Column(name = "aid", nullable = false)
	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	@Column(name = "rid", nullable = false)
	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}
}
