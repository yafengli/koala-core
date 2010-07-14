package factory.factorymethod;

import factory.Crop;
import factory.CropEnum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phoenixup
 */
public class FactoryTest {

    public static final String factoryName_a = "factory.factorymethod.CropFactoryA";
    public static final String factoryName_b = "factory.factorymethod.CropFactoryB";

    public static void main(String args[]) {
        List<Crop> list = new ArrayList<Crop>();
        try {
            /*配合动态类加载系统使用的工厂类，并产生相应的对象*/
            ACropFactory factorya = (ACropFactory) Class.forName(factoryName_a).newInstance();
            ACropFactory factoryb = (ACropFactory) Class.forName(factoryName_b).newInstance();

            list.add(factorya.factory(CropEnum.spring));
            list.add(factorya.factory(CropEnum.summer));
            list.add(factoryb.factory(CropEnum.autumn));
            list.add(factoryb.factory(CropEnum.winter));
            for (Crop crop : list) {
                crop.work();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
