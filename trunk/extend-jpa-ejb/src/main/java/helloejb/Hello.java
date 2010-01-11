package helloejb;

import javax.ejb.Remote;

@Remote
public interface Hello {

    public void sayHello(String name);
}
