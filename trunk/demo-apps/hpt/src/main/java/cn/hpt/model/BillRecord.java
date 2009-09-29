package cn.hpt.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ht_bill_record")
public class BillRecord {// 划价收费-账单-详单表

	@ManyToOne(fetch = FetchType.EAGER)
	public Medicine medicine;
	@ManyToOne(fetch = FetchType.EAGER)
	public Bill bill;

	public Long bnumber;// 药品数量

	@Id()
	@SequenceGenerator(name = "ht_bill_record_seq", sequenceName = "ht_bill_record_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ht_bill_record_seq")
	public Long bid;

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	
	public Long getBnumber() {
		return bnumber;
	}

	public void setBnumber(Long bnumber) {
		this.bnumber = bnumber;
	}

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
}