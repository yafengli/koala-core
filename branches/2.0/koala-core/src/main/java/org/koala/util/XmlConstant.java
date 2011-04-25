package org.koala.util;

/**
 * @author YaFengLi XML报文的相关属性
 */
public class XmlConstant {
	public static final String PATH_APPLICATION = "xml/application.xml";// 缺省application.dtd的XML

	public static final String PATH_MESSAGE = "xml/message.xml";// 缺省message.dtd的XML

	public static final String PATH_USER = "xml/user.xml";// 缺省user.dtd的XML

	public static final String PATH_JPA = "applicationContext-jpa.xml";// JPA|Spring配置文件

	public static final String UTF8 = "UTF-8";// UTF-8报文格式

	public static final String GBK = "GBK";// GBK报文格式

	public static final String MESSAGE_SUCCESS = "success";// 报文成功

	public static final String MESSAGE_ERROR = "error";// 报文失败


    public static final String APPLICATION_XML="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE application PUBLIC \"-//SYS/SYS Configuration DTD 1.0//EN\" \"http://localhost/dtd/application.dtd\">\n" +
            "<application/>";
    public static final String USER_XML="<!DOCTYPE user PUBLIC \n" +
            "\t\"-//SYS/SYS Configuration DTD 1.0//EN\"\n" +
            "\t\"http://localhost/dtd/user.dtd\">\n" +
            "<user />";
    public static final String MESSAGE_XML="<!DOCTYPE message PUBLIC \n" +
            "\t\"-//SYS/SYS Configuration DTD 1.0//EN\"\n" +
            "\t\"http://localhost/dtd/user.dtd\">\n" +
            "<message></message>";
}
