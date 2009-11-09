package cn.hpt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ht_purchase_record")
public class PurchaseRecord {// 物资-详单表

    @Id()
    @SequenceGenerator(name = "ht_purchase_record_seq", sequenceName = "ht_purchase_record_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ht_purchase_record_seq")
    private Long prnumber;
    @ManyToOne
    private Medicine medicine;

    @ManyToOne
    private Purchase purchase;

    private Long prid;

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Long getPrnumber() {
        return prnumber;
    }

    public void setPrnumber(Long prnumber) {
        this.prnumber = prnumber;
    }

    public Long getPrid() {
        return prid;
    }

    public void setPrid(Long prid) {
        this.prid = prid;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

}