package factory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: phoenixup
 * Date: 2010-7-14
 * Time: 10:03:57
 */
public class CropFactory {

    public static Crop factory(CropEnum name) {
        Crop instance = null;
        switch (name) {
            case spring:
                instance = new CropA();
                break;
            case summer:
                instance = new CropB();
                break;
            case autumn:
                instance = new CropC();
                break;
            case winter:
                instance = new CropD();
                break;
            default:
                instance = new CropA();
                break;
        }
        return instance;
    }

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
