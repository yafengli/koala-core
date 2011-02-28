/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.propertyedit;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 *
 * @author YaFengLi
 */
public class AddressPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        return this.getValue().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("我编辑呀，我编辑！");
        Address add = null;
        if (text != null) {
            String[] attrs = StringUtils.tokenizeToStringArray(text, ",");
            add = new Address();
            add.setStreet(attrs[0]);
            add.setDoorNum(attrs[1]);
            add.setPostCode(attrs[2]);
            this.setValue(add);
        }
    }
}
