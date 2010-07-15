package jdbc;

import org.junit.Before;
import org.junit.Test;
import org.koala.orm.jdbc.IJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * User: phoenixup
 * Date: 2010-7-15
 * Time: 14:58:20
 */
public class JdbcDaoTest {
    public static final Logger logger= LoggerFactory.getLogger(JdbcDaoTest.class);
    public static String sql_1 = "select * from add_book where id=:id";
    public static String sql_2 = "select * from add_book where id=?";
    public static String sql_3 = "select * from add_book where id >=:id";
    public static String sql_4 = "select * from add_book";
    public static String sql_5 = "insert into add_book(id,name,isbn) values(:id,:name,:isbn)";
    public static String sql_6 = "update add_book set id=:id,name=:name,isbn=:isbn where id=:id";
    private ApplicationContext ctx;
    private IJdbcDao dao;

    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext(
                new String[]{"META-INF/spring/applicationContext-common.xml",
                        "META-INF/spring/applicationContext-jdbc.xml",});
        dao = ctx.getBean("baseJdbcDao", IJdbcDao.class);
    }

    @Test
    public void read() {
        System.out.printf("[instance=%s]\n", dao);
        logger.info("#################");
        Book book = new Book();
        book.setId(1L);
        Book result = dao.findForObject(sql_1, book, Book.class);
        logger.info("[{},{}]", result.getIsbn(), result.getName());
        logger.info("#################");
        result = dao.findForObject(sql_2, Book.class, 2L);
        logger.info("[{},{}]", result.getIsbn(), result.getName());
        logger.info("#################");
        List<Book> list = dao.find(sql_3, book);
        for (Book bk : list) {
            logger.info("[{},{}]", bk.getIsbn(), bk.getName());
        }
        logger.info("#################");
        list = dao.find(sql_4,Book.class);
        for (Book bk : list) {
            logger.info("[{},{}]", bk.getIsbn(), bk.getName());
        }
        logger.info("#################");
    }
    @Test
    public void create(){
        Book book=new Book();
        book.setId((long)(Math.random()*1000L));
        book.setName(String.valueOf(book.getId()));
        book.setIsbn(String.valueOf(book.getId()));
        for(int i=0;i<3;i++){
            logger.info("[{},{},{}]",new Object[]{book.getId(),book.getName(),book.getIsbn()});
            dao.save(sql_5,book);
            book.setId(book.getId()+i);
        }
        book.setName(new Date().toString());
        book.setIsbn(new Date().toString());
        dao.update(sql_6,book);
    }
}
