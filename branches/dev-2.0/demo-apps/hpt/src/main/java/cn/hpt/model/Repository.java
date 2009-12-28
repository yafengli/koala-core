package cn.hpt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ht_repository")
public class Repository {// 库存表
	@Id()
	@SequenceGenerator(name = "ht_repository_seq", sequenceName = "ht_repository_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ht_repository_seq")
	private Long aid;

	@ManyToOne
	private Medicine medicine;

	private Integer amount;

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
}