package org.helloworld;

import hellojpa.Message;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Administrator
 */
@Entity
public class Person implements Serializable {

    @Id
    @SequenceGenerator(name = "sg", sequenceName = "person_id_seq")
    @GeneratedValue(generator = "sg", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Basic
    @Column(name = "name", unique = true)
    private String name;
    @Basic
    @Column(name = "address", unique = true)
    private String address;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Message message;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
