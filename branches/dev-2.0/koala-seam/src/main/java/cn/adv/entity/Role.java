package cn.adv.entity;

import org.hibernate.validator.Length;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adv_role")
public class Role implements Serializable {

    //seam-gen attributes (you should probably edit these)
    private Long rid;
    private String roledesc;
    private String rolename;

    //add additional entity attributes

    //seam-gen attribute getters/setters with annotations (you probably should edit)

    @Id
    @GeneratedValue
    @Column(name = "rid")
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Length(min = 5, max = 500)
    @Column(name = "role_description")
    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    @Length(min = 4, max = 50)
    @Column(name = "role_name")
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}

