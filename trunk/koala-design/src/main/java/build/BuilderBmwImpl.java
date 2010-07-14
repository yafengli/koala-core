package build;

/**
 * User: phoenixup
 * Date: 2010-7-14
 * Time: 15:30:49
 */
public class BuilderBmwImpl implements IBuilder {
    @Override
    public void buildA() {
        System.out.println("A");
    }

    @Override
    public void buildB() {
        System.out.println("B");
    }

    @Override
    public void buildC() {
        System.out.println("C");
    }

    @Override
    public void make() {
        buildA();
        buildB();
        buildC();
        System.out.println("Make");
    }
}
