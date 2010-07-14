package factory.factorymethod;

import factory.Crop;
import factory.CropEnum;

/**
 *
 * @author phoenixup
 */
public abstract class ACropFactory {
    public abstract Crop factory(CropEnum NAME);
}
