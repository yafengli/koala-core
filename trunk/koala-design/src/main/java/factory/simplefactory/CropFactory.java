package factory.simplefactory;

import factory.Crop;
import factory.CropA;
import factory.CropB;
import factory.CropC;
import factory.CropD;
import factory.CropEnum;
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
}
