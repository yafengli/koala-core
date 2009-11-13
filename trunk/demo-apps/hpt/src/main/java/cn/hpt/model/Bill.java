package cn.hpt.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ht_bill")
public class Bill {// 划价收费-账单-流水号表

    @Id()
    @SequenceGenerator(name = "ht_rid_seq", sequenceName = "ht_bill_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ht_rid_seq")
    private Long rid;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "bill")
    private Set<BillRecord> billrecords;
    private Timestamp rcreatedate;
    private String idnumber;
    private String username;
    private Float pricenum;

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Set<BillRecord> getBillrecords() {
        return billrecords;
    }

    public void setBillrecords(Set<BillRecord> billrecords) {
        this.billrecords = billrecords;
    }

    public Timestamp getRcreatedate() {
        return rcreatedate;
    }

    public void setRcreatedate(Timestamp rcreatedate) {
        this.rcreatedate = rcreatedate;
    }

    public Float getPricenum() {
        return pricenum;
    }

    public void setPricenum(Float pricenum) {
        this.pricenum = pricenum;
    }
}
