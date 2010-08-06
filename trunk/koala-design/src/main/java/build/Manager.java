package build;

/**
 * User: phoenixup
 * Date: 2010-7-14
 * Time: 15:28:37
 */
public class Manager {
    private IBuilder builder;
    public Manager(IBuilder builder){
        this.builder=builder;
    }
    public void build(){
        builder.buildA();
        builder.buildB();
        builder.buildC();
    }
}
