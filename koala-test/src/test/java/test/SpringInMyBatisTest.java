package test;

import demo.Blog;
import demo.BlogMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: Administrator
 * Date: 11-9-13
 * Time: 上午10:22
 */
public class SpringInMyBatisTest {
    private ApplicationContext ctx = null;

    @Before
    public void init() {
        try {
            ctx = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext-*.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelect() {
        BlogMapper mapper = ctx.getBean(BlogMapper.class);
        Blog blog = mapper.selectBlog(1);
        System.out.println(blog.getMessage());
        Blog sblog = new Blog();
        sblog.setId(1L);
        sblog.setTablename("blog");
        System.out.println(sblog.getMessage());
        Blog blog2 = mapper.selectBlogByTableName(sblog);
        System.out.println(blog2.getMessage());
    }

}
