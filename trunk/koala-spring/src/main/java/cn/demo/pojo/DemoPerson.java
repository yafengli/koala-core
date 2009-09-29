/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.demo.pojo;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class DemoPerson implements Serializable {

    private Long id;
    private String name;
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
