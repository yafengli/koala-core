/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloejb.impl;

import helloejb.Hello;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Administrator
 */
@Stateless(name="HelloBeanRemote")
@Remote(Hello.class)
public class HelloWorld implements Hello {

    public void sayHello(String name) {
        System.out.printf("Hello World,Mr. %s!", name);
    }
}
