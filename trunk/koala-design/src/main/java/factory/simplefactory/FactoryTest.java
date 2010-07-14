

package factory.simplefactory;

import factory.Crop;
import factory.CropEnum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phoenixup
 */
public class FactoryTest {
    public static void main(String args[]) {
        List<Crop> list = new ArrayList<Crop>();
        list.add(CropFactory.factory(CropEnum.spring));
        list.add(CropFactory.factory(CropEnum.summer));
        list.add(CropFactory.factory(CropEnum.autumn));
        list.add(CropFactory.factory(CropEnum.winter));
        for (Crop crop : list) {
            crop.work();
        }
    }
}
