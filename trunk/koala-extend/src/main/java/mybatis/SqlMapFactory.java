package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

/**
 * User: phoenixup
 * Date: 2010-7-7
 * Time: 14:32:09
 */
public class SqlMapFactory {
    public static SqlSessionFactory sqlMap;

    static {
        try {
            String resource = "mybatis/configuration.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMap = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        SqlSession session = sqlMap.openSession();
        try {
            /*book*/
//            Book book = (Book) session.selectOne("mybatis.BookMapper.selectSingle", 1);
            User user = (User) session.selectOne("mybatis.BookMapper.selectSingleUser", -1);
            System.out.println("#user" + user.toString());
            if (user != null) {
                System.out.printf("[%s,%s]\n", user.getId(), user.getUsername());
            }
//            book.setTablename("add_book");
//            Book b2 = (Book) session.selectOne("mybatis.BookMapper.selectTableSingle", book);
//            if (b2 != null) {
//                System.out.printf("[%s,%s,%s]\n", b2.getName(), b2.getIsbn(), book.getTablename());
//            }
            System.out.println("*****************************");
            /*mapper*/
            BookMapper mapper = session.getMapper(BookMapper.class);
//            Book mbook = mapper.selectSingle(1L);
//            if (mbook != null) {
//                System.out.printf("[%s,%s]\n", mbook.getName(), mbook.getIsbn());
//            }
            System.out.println("*****************************");
            /*next*/
//            List<Book> list = session.selectList("mybatis.BookMapper.selectAll.next", new Book());
//            for (Book bk : list) {
//                System.out.printf("[%s,%s]\n", bk.getName(), bk.getIsbn());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
