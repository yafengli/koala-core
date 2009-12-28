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
    private Long cid;

    private String name;

    private String describe;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}