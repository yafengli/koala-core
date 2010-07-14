package factory.factorymethod;

import factory.Crop;
import factory.CropA;
import factory.CropB;
import factory.CropEnum;

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
