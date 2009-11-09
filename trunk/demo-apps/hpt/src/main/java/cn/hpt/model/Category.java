package cn.hpt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ht_category")
public class Category {// 药物-分类表

    @Id()
    @SequenceGenerator(name = "ht_category_seq", sequenceName = "ht_category_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ht_category_seq")
    public Long cid;

    public String cname;

    public String cdesc;


    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }
}