package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

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
            Book book = (Book) session.selectOne("mybatis.BookMapper.selectSingle", 1);
            if(book!=null){
                System.out.printf("[%s,%s]\n", book.getName(), book.getIsbn());
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
