package test.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: liyafeng
 * Date: 2007-12-13
 * Time: 12:17:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TEST_IUSER")
public class IUser implements Serializable {

    private Integer id;
    protected String username;
    protected String password;
    protected String age;

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "TEST_IUSER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
