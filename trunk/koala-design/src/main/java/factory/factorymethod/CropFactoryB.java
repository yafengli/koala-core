package factory.factorymethod;

import factory.product.Crop;
import factory.product.CropC;
import factory.product.CropD;
import factory.product.CropEnum;

/**
 * @author phoenixup
 */
public class CropFactoryB extends ACropFactory {
    @Override
    public Crop factory(CropEnum name) {
        Crop instance = null;
        switch (name) {
            case autumn:
                instance = new CropC();
                break;
            case winter:
                instance = new CropD();
                break;
            default:
                instance = new CropC();
                break;
        }
        return instance;
    }
}
