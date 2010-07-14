package factory.factorymethod;

import factory.product.Crop;
import factory.product.CropEnum;

/**
 *
 * @author phoenixup
 */
public abstract class ACropFactory {
    public abstract Crop factory(CropEnum NAME);
}
