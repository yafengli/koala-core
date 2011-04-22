package cn.demo.dao.impl;

import cn.demo.dao.DemoPersonDao;
import cn.demo.pojo.DemoPerson;
import org.koala.dao.GenericDaoJDBC;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yafengli
 */
@Service("cn.neto.dao.impl.DemoPersonDaoImpl")
public class DemoPersonDaoImpl extends GenericDaoJDBC<DemoPerson, Long> implements DemoPersonDao {

    public ParameterizedRowMapper<DemoPerson> getRowMapper() {
        return new ParameterizedRowMapper<DemoPerson>() {

            public DemoPerson mapRow(ResultSet rs, int num) throws SQLException {
                DemoPerson dp = new DemoPerson();
                try {
                    dp.setId(rs.getLong("ID"));
                } catch (Exception e) {
                }
                try {
                    dp.setName(rs.getString("NAME"));
                } catch (Exception e) {
                }
                try {
                    dp.setVersion(rs.getInt("VERSION"));
                } catch (Exception e) {
                }
                return dp;
            }
        };
    }

    public void addInvoke() {
        System.out.println("Hello add invoke.");
    }
}
