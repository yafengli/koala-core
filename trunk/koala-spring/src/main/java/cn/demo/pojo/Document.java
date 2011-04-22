package cn.demo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-12-18
 * Time: 17:45:23
 * To change this template use File | Settings | File Templates.
 */
public class Document implements Serializable {
    private String title;
    private Integer type;
    private Date date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
