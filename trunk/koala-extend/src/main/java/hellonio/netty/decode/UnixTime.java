package hellonio.netty.decode;

import java.util.Date;

/**
 * User: phoenixup
 * Date: 2010-7-5
 * Time: 10:26:05
 */
public class UnixTime {
    private int value;
    private int age;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UnixTime(int value,int age) {
        this.setValue(value);
        this.setAge(age);
    }

    @Override
    public String toString() {
        return new Date(this.getValue() * 1000).toString()+"|"+String.valueOf(this.getAge());
    }
}
