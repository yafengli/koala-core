package test.model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: liyafeng
 * Date: 2007-12-13
 * Time: 11:08:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TEST_EX_USER")
public class ExUser extends IUser {
    protected String exkey;
    public String getExkey() {
        return exkey;
    }

    public void setExkey(String exkey) {
        this.exkey = exkey;
    }
}
