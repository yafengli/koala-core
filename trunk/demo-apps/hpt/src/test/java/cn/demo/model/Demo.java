package cn.demo.model;

import javax.persistence.*;

/**
 * Date: 2009-9-7 Time: 16:09:49
 * 
 * @version 1.0
 * @authtor YaFengLi
 */
@Entity
@Table(name = "t_demo")
public class Demo {
	@Id()
	@SequenceGenerator(name = "did_seq", sequenceName = "t_demo_did_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "did_seq")
	private Long did;
	@Column(name = "d_name", unique = false, nullable = false, length = 8)
	private String name;
	private String desc;

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
