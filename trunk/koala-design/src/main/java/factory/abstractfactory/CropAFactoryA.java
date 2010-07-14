package factory.abstractfactory;

import factory.Crop;
import factory.CropA;
import factory.CropB;
import factory.CropC;
import factory.CropD;
import factory.CropEnum;

/**
 *
 * @author phoenixup
 */
public class CropAFactoryA implements ICropFactory  {

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
                instance=new CropC();
                break;
            case winter:
                instance=new CropD();
                break;
            default:
                instance = new CropA();
                break;
        }
        System.out.printf("[##%s##]\n",this.getClass().getName());
        return instance;
    }
}
