package factory.abstractfactory;

import factory.product.Crop;
import factory.product.CropEnum;

/**
 * @author phoenixup
 */
public interface ICropFactory {

    public Crop factory(CropEnum NAME);
}
