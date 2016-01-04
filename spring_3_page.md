### Web配置 ###
_**web.xml**_
```
<servlet>
        <servlet-name>viewDispatcher_1</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
			<!--
			不定义contextConfigLocation属性值，缺省为${servlet-name}-servlet.xml。
			-->
            <param-value>/WEB-INF/config/webmvc-servlet.xml</param-value>
        </init-param>
        <load-on-startup>0</load-on-startup>
</servlet>    
<servlet>
        <servlet-name>viewDispatcher_2</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/config/freemarker-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
        <servlet-name> viewDispatcher_1<serrvlet-name>
        <url-pattern>*.view1</url-pattern>
</servlet-mapping>
<servlet-mapping>
   <servlet-name> viewDispatcher_2<servlet-name>
    <url-pattern>*.view2</url-pattern>
</servlet-mapping>
```

**补充：**配置Web应用程序，让满足Mapping的请求使用定义的Servlet处理，可以配置多个DispathcerServlet来使用不同的配置文件，满足不同要求的处要求（比如当同时使用不兼容的视图<JSP and Freemarker>时）。

Spring配置
国际化配置
> 在配置文件中，添加内容：
```
<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource"
		p:basenames="WEB-INF/i18n/messages,WEB-INF/i18n/application"
		p:fallbackToSystemLocale="false" />
<!—或者-->
<bean id="messageSource"
class="org.springframework.context.support.ResourceBundleMessageSource">
<property name="basename" value="messages"/>
</bean>
```
在JSP视图中可以使用`<spring:message code="message_name_key"/>`
控制器、请求映射、返回
使用@Controller注解将bean定义为控制器，使用\@RequestMapping为bean或者方法定义请求的映射处理关系。使用@PathVariable定义映射URI解析参数，使用@ModelAttribute定义模型属性。
```
@Controller
@RequestMapping("/freemarker")
public class FreemarkerViewController {
@ModelAttribute
public 
/*view name: prefix+return value+suffix*/
	@RequestMapping("/spftest/{id}")
	public String spftest(@PathVariable String id,Model model) {
		model.addAttribute("_id", id);
		return "spftest";
	}
/*view name: prefix+@RequestMappinge+suffix*/
	@RequestMapping("/hello/{id}")
	public void hello(@PathVariable String id,Model model) {
		model.addAttribute("_id", id);
	}
/*merge model and view return, view name:prefix+viewname+suffix*/
@RequestMapping("view/{id}")
	public ModelAndView view(@PathVariable String id, Model model) {
		model.addAttribute("_id", id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view");
		return mav;
	}
/*return http response body,use with json formatter.*/
@RequestMapping("/ajax/{name}/{age}")
	@ResponseBody
	public FormInfo ajax(@PathVariable("name") String name,
			@PathVariable("age") Integer age,
			@ModelAttribute("type") FormInfo info, Model model) {
		logger.info("@ajax:{},{},{}", new Object[] { info.getName(), info.getAge(),
				info.getBirthday() });
		info.setName(name);
		info.setAge(age);
		return info;
	}
}
```
当@RequestMapping修饰的函数返回值为String字符串并且使用其他返回注解修饰，则生成的视图名规则：前缀+返回值+后缀。当使用ModelAndView作为返回值类型时，生成的视图规则与String相同。

当@RequestMapping修饰的函数返回值为空时，则生成的视图名规则：
前缀+@RquestMappingvalue值+后缀。

当使用@ResponseBody修饰返回内容时，返回的内容一般使用Json格式转换，这样客户端可以是用JavaScript脚本直接处理。JSON的转换需要在mvc-servlet.xml文件中增加配置：
```
<bean id="mappingJacksonHttpMessageConverter"
	class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter" />
<bean
class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
	<property name="messageConverters">
		<list>
			<ref bean="mappingJacksonHttpMessageConverter" />
    </list>
	</property>
</bean>
```
定制数据属性编辑
> 最常见的是在将视图中表单提交的String类型值与Date类型值进行装换，在@Controller中用@InitBinder注解方法：
```
@InitBinder
public void initBinder(WebDataBinder binder) {
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
dateFormat.setLenient(false);
binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
}
```
### 视图 ###
#### JSP&Tiles ####
> 如果需要使用Tiles 2装饰JSP作为Spring MVC视图，配置Spring MVC Configuration文件，增加内容：
```
<bean class="org.springframework.web.servlet.view.tiles2.TilesConfigurer"
	id="tilesConfigurer">
	<property name="definitions">
		<list>
			<value>/WEB-INF/layouts/layouts.xml</value>
			<!-- Scan views directory for Tiles configurations -->
			<value>/WEB-INF/views/**/views.xml</value>
		</list>
	</property>
</bean>
<bean id="tiewResolver"
	class="org.springframework.web.servlet.view.UrlBasedViewResolver">
	<property name="viewClass"
		value="org.springframework.web.servlet.view.tiles2.TilesView" />
</bean>
```
Tiles 2的配置文件layoutx.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE tiles-definitions PUBLIC
       "-//Apache Software Foundation//DTD Tiles Configuration 2.1//EN"
       "http://tiles.apache.org/dtds/tiles-config_2_1.dtd">
<tiles-definitions>
	<definition name="public" template="/WEB-INF/layouts/default.jspx">
		<put-attribute name="header" value="/WEB-INF/views/jsp/common/header.jspx" />
		<put-attribute name="footer" value="/WEB-INF/views/jsp/common/footer.jspx" />
	</definition>
</tiles-definitions>
```
JSP文件default.jspx
```
<html xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:spring="http://www.springframework.org/tags">
<jsp:directive.page contentType="text/html;charset=UTF-8" />
<jsp:directive.page pageEncoding="UTF-8" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body class="tundra spring">
	<div id="wrapper">
		<tiles:insertAttribute name="header" ignore="true" />
		<tiles:insertAttribute name="menu" ignore="true" />
		<div id="main">
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="footer" ignore="true" />
		</div>
	</div>
</body>
</html>
```
#### Freemarker ####
> 修改Spring MVC Configuration文件，增加以下内容：
```
<!-- Freemarker Configuration-->
	<bean id="freemarkerConfig"
		class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
		<property name="templateLoaderPath" value="/WEB-INF/views/ftl" />
		<property name="defaultEncoding" value="UTF-8"/><!—解决中文乱码@1-->
		<property name="freemarkerSettings">
			<props>				
				<prop key="number_format">#</prop><!-- 数字格式化-->
			</props>
		</property>
	</bean>
	<bean id="freeMarkerViewResolver"
		class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
		<property name="cache" value="true" />
		<property name="prefix" value="" />
		<property name="suffix" value=".ftl" />
		<!—解决中文乱码@2-->
		<property name="contentType" value="text/html;charset=UTF-8" />		
	</bean>
```
> Spring提供了在freemarker模板文件中的标签支持，将spring-webmvc-xxx.jar文件的spring.ftl文件复制到freemarker configuration中定义的templateLoaderPath目录或子目录下。
在需要使用标签的文件中添加:
`<#import "/path/spring.ftl" as sp>`

#### Sitemesh ####
> 使用sitemesh模板技术，需要在WEB-INF/web.xml文件中配置：
```
<filter>
	<filter-name>sitemesh</filter-name>
	<filter-class>com.opensymphony.sitemesh.webapp.SiteMeshFilter</filter-class>
</filter>
<filter-mapping>
	<filter-name>sitemesh</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```
缺省在sitemesh的装饰配置文件decorators.xml支持JSP模板定义，如果要使用freemarker定义模板，需要在WEB-INF/web.xml中添加配置：
```
<servlet>
	<servlet-name>FreemarkerDecorator</servlet-name>
	<servlet-class>com.opensymphony.module.sitemesh.freemarker.FreemarkerDecoratorServlet</servlet-class>
	<init-param>
		<param-name>TemplatePath</param-name>
		<param-value>/</param-value>
	</init-param>
	<init-param>
		<param-name>default_encoding</param-name>
		<param-value>UTF-8</param-value>
	</init-param>
	<load-on-startup>0</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>FreemarkerDecorator</servlet-name>
	<url-pattern>*.ftl</url-pattern>
</servlet-mapping>
```
然后在decorators.xml文件定义：
```
<decorators defaultdir="/WEB-INF/_decorators/ftl">
    <decorator name="main" page="main.ftl">
          <pattern>/freemarker/*.view</pattern>
    </decorator>
</decorators>
```
就可以使用freemarker编写的模板文件main.ftl：
```
<html>
	<head>
	${title}
    ${head}
    </head>
    <body>
    <#include "/WEB-INF/_decorators/ftl/header.ftl">    
 	<div>
		${base}|${title}<p/>   
      	${body}
	</div> 	
 	<#include "/WEB-INF/_decorators/ftl/footer.ftl">
 	</body>
</html>
```
_**避免表单重复提交**_
使用redirect:前缀。
如表单的Action对应的方法：
```
@RequestMapping("/index/form")
public String form(@ModelAttribute("type") @Valid FormInfo info,
		BindingResult result) {
	if (result.hasErrors()) {
		return "index";
	} else {
		return "redirect:/tiles/form.view";
	}
}
```
#### 多视图配置 ####
1.	使用ResourceBundleViewResolver配置内容：
```
< bean id="r" class="org.springframework.web.servlet.view.ResourceBundleViewResolver">
    <property name="basename" value="views"/>
<property name="order" value="0"/>
</bean>

<!-- 对于FreeMarker等还需要有额外的配置-->
<bean class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer" 
          id="freemarkerConfig" p:templateLoaderPath="/WEB-INF/ftl/"/>
```
在views.properties中配置属性：[视图名].class、[视图名].url
```
helloj.class=org.springframework.web.servlet.view.JstlView
helloj.url=WEB-INF/jsp/helloj.jsp

hellof.class=org.springframework.web.servlet.view.freemarker.FreeMarkerView
hellof.contentType=text/html;charset=UTF-8
hellof.url=hellof.ftl
```
2.	视图链配置
当一个配置文件中定义多个ViewResolver的时候，使用

&lt;property name=”order” value=”1”/&gt;

，value的数值越小，优先级越高。

正常情况下每个Controller的执行方法都应该返回一个ModelAndView，正如名称所显示的，ModelAndView是用来封装视图和显示数据集合的一个结果类。ViewResolver透过对ModelAndView的分析渲染视图，并动态填充视图中的数据内容。

> 注意：一般有特定要求的视图使用ResourceBundleViewResolver在属性文件中定义class和url。大部分其他视图使用视图链顺序配置解析，其中InternalResourceViewResolver、FreeMarkerViewResolver、VelocityViewResolver等无法返回null的ViewResolver，因为调用了RequestDispatcher方法，只能调用一次而且不能预先知道视图是否能成功渲染，必须放在视图链的最末端，否则其后的ViewResolver无法生效。

验证
> 可以使用实现JSR 303规范的的Hiberate Validator 4+版本与Spring集成使用，在需要约束的Bean中对属性添加约束注解@NotEmpty、@Size、@Length等。
```
public class FormInfo {
	@NotEmpty
	private String name;
	@Length(min = 2, max = 20)
	private String address;
	@Min(10)
	@Max(200)
	private Integer age;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	//sets and gets
}
```
如果需要自定义validation messages，classpath目录中建立新的ValidationMessages_[local](local.md).properties，然后你将hibernate-validator-xxx.jar中的ValidationMessages.properties中的内容复制到这文件中修改。
在Spring MVC中的Controller中可以使用@Vaild注解对bean进行验证。
```
@InitBinder
public void initBinder(WebDataBinder binder) {
	binder.setValidator(validator);
}	
@RequestMapping("/form")
public String form(@ModelAttribute("type") @Valid FormInfo info){
	//do something
}
```
**注解配置**
需要使用XSD定义
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
	http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd">	
</beans>
```
注解注入配置
```
<!-- 声明式事务管理，处理Hibernate、JPA、Ibatis DAO模型 -->
	<tx:annotation-driven transaction-manager="transactionManager" />
	<!-- 注释配置 -->
	<context:annotation-config />
    <!-- 配置组件扫描的路径（包名） -->
	<context:component-scan base-package="scan.package.name" />
```
Spring扩展注解
主要使用的注解
@Service  @Compenent
注册为Context中的Bean，可以指定注入的名称。
@Autowired
自动注入依赖，一JNDI，二Type-match类型匹配。
@Qualfier
自动注入依赖的名称过滤_

JSR-250注解
@Resource
注解被用来激活一个命名资源的依赖注入，在JavaEE应用程序中，该注解被典型地转换为绑定于JNDI context中的一个对象。 Spring支持使用@Resource通过JNDI lookup来解析对象，默认地，拥有与@Resource注解所提供名字相匹配的“bean name（bean名字）”的Spring管理对象会被注入。与spring扩展中的@Autowired、@Qualfier功能类似。
@PostConstruct 、@PreDestroy
注解分别用来触发Spring的初始化和销毁回调。
参考程序包：test.annotation.config


AOP配置：注解定义
1.	编辑Spring配置文件，加入以下内容：
> <!-- AOP配置 -->
> 

&lt;aop:aspectj-autoproxy /&gt;



2.	定义Aop切面的方法。
3.	在需要使用Aop切面方法的类中定义注解。
参考程序包：test.annotation.aop


配置属性：注解定义
1.	自定义Annotation满足注解注入的Bean可以使用属性文件中的配置内容。
```
	<!-- 属性文件读取 -->
	<bean id="loader"
		class="org.koala.spring.AnnotationBeanPostProcessor">
		<property name="locations">
			<list>
				<value>classpath:jdbc.properties</value>
			</list>
		</property>
	</bean>
```
其中jdbc.properties为提供的属性文件

2.	在配置文件中代替PropertyPlaceholderConfiguter就可以在类中使用如：
```
@Service  
public class PropertiesLoader  implements Serializable {   
  
    @Properties(name="pic.address" )   
    private String picAddress;   
  
    @Properties(name="pic.url" )   
    private String picUrl;   
   
}  
```

使用Annotation配置Spring MVC
@Controller
> 作用于Class，定义Controller的名称，为Spring Bean的id值。
@RequestMapping
> 作用于Class和Method，定义URL映射的匹配的执行方法，可定义的属性值有
value，params，method。
@ResponeBody
> 返回响应内容体
@RequestParam
> 作用于执行方法的参数，用户提取请求中的参数值。
@ModelAttribute
> 作用与方法和参数，当作用于方法时为View提供的应用数据，与ModelAndView中的Map对应。当作用于参数时用于映射一个模型属性到特定注解的方法参数，这是控制器获得持有表单数据的对象引用的方法，在使用绑定这个参数的模型必须先绑定它，
@SessionAttibute
作用于Class，通常会列出模型属性的名称，这些属性被透明的保存在会话或者对话存储中，用于在后续的请求之间作为表单支持。

_**DemoContorller.java**_
```
@Controller("democontroller")
public class DemoContorller {
    @RequestMapping("/helloj.action")
    public ModelAndView helloj(@RequestParam(required = false, value = "id") String id) {
        ModelAndView mav = new ModelAndView("helloj");
        Map map = new HashMap();
        map.put("message", String.format("This is demo message!The id is [%s]", id));
        mav.addAllObjects(map);
        return mav;
    }
}
```

_**applcationContext.xml**_
```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
       xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"> 

<context:annotation-config />    
<context:component-scan base-package="demo.scan.controller"/>
***
</beans>
```

表单、验证

依赖注入DI(控制反转)
name id区别
bean定义中id为XML推荐的标识必须为有效字符，一个类型多个bean定义id，则其他id可以认为是别名。另外如果定义多个标识符，或者不符合XML合法id有效字符作为标识符可以使用name属性处理，name属性值可以用逗号、冒号、空格分割多个标识符。

延迟加载
<beans default-lazy-init="true" …>可以指定缺省延迟加载规则，也可以用<bean lazy-init="false" …>来指定单独bean的延迟加载策略。

自动装载
可以在使用default-autowire在beans指定全局的缺省自动加载的策略。也可以使用autowire对bean指定特定的加载策略。autowire会减少配置文件内容；autowire会自动与java源程序同步；autowire可能导致类型冲突；autowire对文档依赖生成是个难题。

作用域


&lt;aop:scoped-proxy proxy-target-class="false"/&gt;

定义在session、request或者自定义的作用域内，当bean被注入到其他bean时，可以透过代理动态的获得真正的目标对象。当proxy-target-class="false"时使用JDK动态代理必须提供bean的接口定义，否则必须要引入CGLB包。

容器扩展
可以通过扩展BeanPostProcessor对bean提供自定义（或默认地来覆盖容器）的实例化逻辑、依赖解析逻辑，完成bean的实例化、配置和其它的初始化后执行一些自定义逻辑。
```
AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{
           "applicationContext-common.xml", "applicationContext-jdbc.xml"});
ctx.getBeanFactory().addBeanPostProcessor(new BeanPostProcessor() {
   public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
           System.out.printf("[-------after------][%s,%s]", o.toString(), s);
           return o;
    }
   public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
          System.out.printf("[-------before------][%s,%s]", o.toString(), s);
           return o;
}
});
```
BeanFactoryPostProcessor可以对bean的定义（配置元数据）进行处理。允许其在容器实际实例化任何其它的bean之前读取配置元数据并可以修改它。
```
XmlBeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));
// bring in some property values from a Properties file
PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
cfg.setLocation(new FileSystemResource("jdbc.properties"));
// now actually do the replacement
cfg.postProcessBeanFactory(factory);
```

方法注入
一般当bean要引用另一个bean时，只要将被引用的bean定义为property就可以了，但是有一些情况是有区别的，比如两个bean的生命周期不同，构建实例规则不同。比如一个singleton bean引用prototype bean，则singleton bean只会在初始化的时候创建一个prototype bean并一直使用他，而prototype bean的多实例规则将失效。
这个时候有两种办法处理，一是放弃依赖注入，使用提供BeanFactoryAware实现的办法提供prototype bean的创建。
```
public class CommandManager implements BeanFactoryAware{
private BeanFactory beanFactory;
//覆盖方法
public void setBeanFactory(BeanFactory beanFactory){
    this.beanFactory=beanFactory;
}
//业务处理
public Object process(){
    T t=createT();
    return t.action();
}
//创建对象
public T createT(){
    return (T)this.beanFactory.getBean("t");
}
}
```
第二种方法：使用lookup方法注入bean。在被注入的bean中定义创建注入bean的抽象方法。
```
public abstract class CommandManager{
    //声明创建注入bean的抽象方法，容器会创建实现此方法的动态子类
public abstract T createT();
//业务处理
public Object process(){
    T t=createT();
    return t.action();
}
}
```
```
<bean id="t" class="x.xx.T" scope="prototype"/>
<bean id="commandManager" class="x.xx.CommandManager" scope="singleton">
        <lookup-method name="createT" bean="t"/>
</bean>
```
内建PropertyEditor的实现
Spring中提供了大量的内建PropertyEditor提供Object与String之间的转化。自定义PropertyEditor也非常容易。
定义bean类型；
继承PropertyEditorSurport定义bean类型的PropertyEditor；
注册PropertyEditor；
参考：test.propertyedit.