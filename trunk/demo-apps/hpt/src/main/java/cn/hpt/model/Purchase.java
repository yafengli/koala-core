package cn.hpt.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ht_purchase")
public class Purchase {//物资-流水号表

	@Id()
	@SequenceGenerator(name = "ht_purchase_seq", sequenceName = "ht_purchase_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ht_purchase_seq")
    public Long pid;

    public Timestamp pcreatedate;

    @OneToMany(fetch=FetchType.LAZY,mappedBy="purchase")
    public Set<PurchaseRecord> purchaserecord;

    
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Timestamp getPcreatedate() {
		return pcreatedate;
	}

	public void setPcreatedate(Timestamp pcreatedate) {
		this.pcreatedate = pcreatedate;
	}

	public Set<PurchaseRecord> getPurchaserecord() {
		return purchaserecord;
	}

	public void setPurchaserecord(Set<PurchaseRecord> purchaserecord) {
		this.purchaserecord = purchaserecord;
	}    
}