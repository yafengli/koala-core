package cn.demo.vo;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * User: phoenixup
 * Date: 2010-7-12
 * Time: 11:08:23
 */
public class Account {

    @NotNull
    @Size(min = 12, max = 70)
    private String id;
    @NotNull
    private String name;
    @NotNull
    private Timestamp time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
