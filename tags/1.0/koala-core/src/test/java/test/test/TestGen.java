package test.test;


import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Date: 2009-9-24
 * Time: 14:09:26
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class TestGen {
    public <T extends Object,K extends Serializable> List<T> make(T first,K k) {
        System.out.printf("[%s]\n[%s]\n",first.getClass().getName(),k.getClass().getName());
        return new ArrayList<T>();
    }
    public static void main(String[]args){
        TestGen tg=new TestGen();
        tg.make("One",1);
        tg.make(1,"One");
        tg.make(1.0,true);
        tg.make(true,"232");
    }
}

