package test;

import com.greatbit.jdbc.mapper.GenericMapperTemplate;
import com.greatbit.jdbc.mapper.SimpleMapperTemplate;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MapperTest {
    private GenericMapperTemplate template = new SimpleMapperTemplate("mybatis-conf.xml", "test.Generic");

    @Test
    public void testXml() {
        List<Changelog> changelogs = template.findList("finds", null, Changelog.class);
        System.out.println("@@@@@@@@@@@@@@@@@@");
        for (Changelog log : changelogs) {
            System.out.printf("%s,%s,%s\n", log.getId(), log.getApplied_at(), log.getDescription());
        }
        System.out.println("@@@@@@@@@@@@@@@@@@");
    }

    @Test
    public void testAnnotation() {
        SqlSession session = template.getsSqlSessionFactory().openSession();
        DemoMapper demoMapper = template.getMapper(session, DemoMapper.class);

        System.out.println("################");
        List<Changelog> changelogs = demoMapper.selectAll();
        for (Changelog log : changelogs) {
            System.out.printf("%s,%s,%s\n", log.getId(), log.getApplied_at(), log.getDescription());
        }
        System.out.println("################");
        Changelog log = demoMapper.selectSingle(3L);
        System.out.printf("%s,%s,%s\n", log.getId(), log.getApplied_at(), log.getDescription());
        System.out.println("################");

        log.setId(System.currentTimeMillis());
        demoMapper.insert(log);
        session.commit();
        session.close();
    }
}
