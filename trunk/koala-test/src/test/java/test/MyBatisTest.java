package test;

import demo.Blog;
import demo.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;

/**
 * User: Administrator
 * Date: 11-9-13
 * Time: 上午10:22
 */
public class MyBatisTest {
    private SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        try {
            Reader reader = Resources.getResourceAsReader("META-INF/configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelect() {
        BlogMapper blogMapper = sqlSessionFactory.openSession().getMapper(BlogMapper.class);
        System.out.printf("%s,%s\n", blogMapper, sqlSessionFactory);
        Blog blog = blogMapper.selectBlog(1);
        System.out.printf("%d,%s\n", blog.getId(), blog.getMessage());
        Blog blog2=new Blog();
        blog2.setTablename("blog");
        blog2.setId(1L);
        Blog blog3=blogMapper.selectBlogByTableName(blog2);
        System.out.printf("%d,%s\n", blog3.getId(), blog3.getMessage());
    }
}
