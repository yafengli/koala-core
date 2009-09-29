package cn.hpt.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public class PropertiesLoader extends ResourceBundle {

	private String resource;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	private Properties props;

	public PropertiesLoader() {

	}

	public void init() {
		try {
			props = new Properties();
			System.out.println("[resource:]"+this.getResource());
			props.loadFromXML(this.getClass().getResourceAsStream(
					this.getResource()));
		} catch (Exception e) {
		}

	}

	@Override
	protected Object handleGetObject(String key) {
		return props.getProperty(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		Set<String> handleKeys = props.stringPropertyNames();
		return Collections.enumeration(handleKeys);
	}
}
