package factory.abstractfactory;

import factory.Crop;
import factory.CropEnum;

/**
 *
 * @author phoenixup
 */
public interface ICropFactory {

    public Crop factory(CropEnum NAME);
}
