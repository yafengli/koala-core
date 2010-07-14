package factory.abstractfactory;

import factory.factorymethod.*;
import factory.Crop;
import factory.CropEnum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phoenixup
 */
public class FactoryTest {

    public static final String afactoryName_a = "factory.abstractfactory.CropAFactoryA";
    public static final String afactoryName_b = "factory.abstractfactory.CropAFactoryB";

    public static void main(String args[]) {
        List<Crop> list = new ArrayList<Crop>();
        try {
            /*配合动态类加载系统使用的工厂类，并产生相应的对象*/
            ICropFactory factorya = (ICropFactory) Class.forName(afactoryName_a).newInstance();
            ICropFactory factoryb = (ICropFactory) Class.forName(afactoryName_b).newInstance();

            list.add(factorya.factory(CropEnum.spring));
            list.add(factorya.factory(CropEnum.summer));
            list.add(factoryb.factory(CropEnum.spring));
            list.add(factoryb.factory(CropEnum.summer));
            for (Crop crop : list) {
                crop.work();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
