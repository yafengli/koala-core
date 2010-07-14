package factory;

import factory.abstractfactory.ICropFactory;
import factory.factorymethod.ACropFactory;
import factory.product.Crop;
import factory.product.CropEnum;
import factory.simplefactory.CropFactory;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User: phoenixup
 * Date: 2010-7-14
 * Time: 13:46:44
 */
public class FactoryTest {
    public static final String cfactoryName = "factory.simplefactory.CropFactory";
    public static final String factoryName_a = "factory.factorymethod.CropFactoryA";
    public static final String factoryName_b = "factory.factorymethod.CropFactoryB";
    public static final String afactoryName_a = "factory.abstractfactory.CropAFactoryA";
    public static final String afactoryName_b = "factory.abstractfactory.CropAFactoryB";

    private ACropFactory afactorya;
    private ACropFactory afactoryb;
    private ICropFactory ifactorya;
    private ICropFactory ifactoryb;
    private CropFactory cfactory;

    @Before
    public void init() {
        try {
            /*配合动态类加载系统使用的工厂类，并产生相应的对象*/
            cfactory = (CropFactory) Class.forName(cfactoryName).newInstance();
            afactorya = (ACropFactory) Class.forName(factoryName_a).newInstance();
            afactoryb = (ACropFactory) Class.forName(factoryName_b).newInstance();
            ifactorya = (ICropFactory) Class.forName(afactoryName_a).newInstance();
            ifactoryb = (ICropFactory) Class.forName(afactoryName_b).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void simpleFactory() {
        System.out.println("##[Simle Factory]##");
        print(cfactory);
    }

    @Test
    public void factoryMethod() {
        System.out.println("##[Factory Method]##");
        print(afactorya);
        print(afactoryb);
    }

    @Test
    public void abstractFactory() {
        System.out.println("##[Abstract Factory]##");
        print(ifactorya);
        print(ifactoryb);

    }

    private void print(Object factory) {
        List<Crop> list = new ArrayList<Crop>();
        try {
            if (factory != null) {
                Method md = null;
                if (factory instanceof ACropFactory) {
                    md = ACropFactory.class.getMethod("factory", new Class[]{CropEnum.class});
                } else if (factory instanceof ICropFactory) {
                    md = ICropFactory.class.getMethod("factory", new Class[]{CropEnum.class});
                } else if (factory instanceof CropFactory) {
                    md = CropFactory.class.getMethod("factory", new Class[]{CropEnum.class});
                }
                list.add((Crop) md.invoke(factory, CropEnum.spring));
                list.add((Crop) md.invoke(factory, CropEnum.summer));
                list.add((Crop) md.invoke(factory, CropEnum.autumn));
                list.add((Crop) md.invoke(factory, CropEnum.winter));                                
                for (Crop crop : list) {
                    crop.work();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
