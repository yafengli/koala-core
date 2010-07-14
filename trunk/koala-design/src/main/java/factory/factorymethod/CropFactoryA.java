package factory.factorymethod;

import factory.product.Crop;
import factory.product.CropA;
import factory.product.CropB;
import factory.product.CropEnum;

/**
 *
 * @author phoenixup
 */
public class CropFactoryA extends ACropFactory {

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
            default:
                instance = new CropA();
                break;
        }
        return instance;
    }
}
