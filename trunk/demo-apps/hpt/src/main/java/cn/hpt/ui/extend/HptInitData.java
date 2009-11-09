package cn.hpt.ui.extend;

import cn.hpt.model.Operator;
import cn.hpt.model.Role;
import cn.hpt.util.PropertiesLoader;
import org.koala.dao.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * User: YaFengLi
 * Date: 2009-11-9
 * Time: 15:29:55
 */
@Service
public class HptInitData {
    private static final Logger logger = LoggerFactory.getLogger(HptInitData.class);
    @Autowired
    private IDao baseDao;
    @Autowired
    private PropertiesLoader pl;

    public void init() {
        /*admin and user init*/
        Role admin = new Role();
        admin.setRolename(Role.ADMIN_ROLE);
        admin.setRoledesc(Role.ADMIN_ROLE);
        Role user = new Role();
        user.setRolename(Role.OPERATOR_ROLE);
        user.setRoledesc(Role.OPERATOR_ROLE);
        baseDao.save(user);
        baseDao.save(admin);
        /* opertor init */
        Operator item = new Operator();
        item.setLoginname(pl.getString("init.user"));
        item.setPassword(pl.getString("init.password"));
        item.setDescribe(pl.getString("init.desc"));
        item.setCreatedate(new Timestamp(System.currentTimeMillis()));

        if (admin != null && admin.getRoleid() != null) {
            item.setRole(admin);
            baseDao.save(item);
        } else {
            logger.error("INIT DATA ERROR!");
        }
    }
}
