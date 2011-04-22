/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.adv.info;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 */
public class ViewInfo implements Serializable {

	private String key;
	private Integer value;
	private String desc;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
