package cn.hpt.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ht_medicine")
public class Medicine {// 药品表

    @Id()
    @SequenceGenerator(name = "ht_medicine_seq", sequenceName = "ht_medicine_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ht_medicine_seq")
    private Long mid;
    @Column(unique = true)
    private String mname;// 药品名称
    private String mnumber;// 药品编号
    private String mshortcut;// 药品简称
    private Double price;// 药品价格
    @ManyToOne
    private Category category;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "medicine")
    private Set<PurchaseRecord> purchaseRecords;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "medicine")
    private Set<Repository> repositories;

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMnumber() {
        return mnumber;
    }

    public void setMnumber(String mnumber) {
        this.mnumber = mnumber;
    }

    public String getMshortcut() {
        return mshortcut;
    }

    public void setMshortcut(String mshortcut) {
        this.mshortcut = mshortcut;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Set<PurchaseRecord> getPurchaseRecords() {
        return purchaseRecords;
    }

    public void setPurchaseRecords(Set<PurchaseRecord> purchaseRecords) {
        this.purchaseRecords = purchaseRecords;
    }

    public Set<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(Set<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public String toString() {
        return this.getMname();
    }
}
