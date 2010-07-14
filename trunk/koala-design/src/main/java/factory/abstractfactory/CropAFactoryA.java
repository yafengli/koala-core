package factory.abstractfactory;

import factory.product.*;
import factory.product.CropA;
import factory.product.CropB;
import factory.product.CropC;
import factory.product.CropEnum;

/**
 * @author phoenixup
 */
public class CropAFactoryA implements ICropFactory {
    @Override
    public Crop factory(CropEnum name) {
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
