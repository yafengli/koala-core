package test.test;

import java.util.List;
import org.junit.Test;
import org.koala.dao.ibatis.BaseIBatisDao;
import org.koala.dao.ibatis.IIBatisDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.dao.AccountIBatisDao;
import test.dao.impl.ibatis.AccountDaoImpl;
import test.model.Account;

public class IBatisTest {

    private ApplicationContext ctx = null;
    private AccountIBatisDao iadao = null;
    private IIBatisDao baseDao = null;

    public IBatisTest() {
        ctx = new ClassPathXmlApplicationContext(new String[]{
                    "applicationContext-common.xml", "applicationContext-ibatis.xml"});
        iadao = (AccountIBatisDao) ctx.getBean(AccountDaoImpl.BEANNAME,
                AccountIBatisDao.class);
        baseDao = (BaseIBatisDao) ctx.getBean("baseIBatisDao");
    }

    @Test
    public void ibatis() {
        long id = System.currentTimeMillis();
        Account account = new Account();
        account.setAid(id);
        account.setAname("ibatis");
        account.setAdesc("ibatis-desc");
        account.setAddress("nanjing");
        iadao.save("_save", account);

        Account item = iadao.findById("_findbyid", id);
        if (item != null) {
            System.out.println("[ibatis]" + item.getAddress() + "|" + item.getAname());
        } else {
            System.out.println("[ibatis]It's null.");
        }
    }

    @Test
    public void test2() {
        long id = System.currentTimeMillis();
        Account account = new Account();
        account.setAid(id);
        account.setAname("ibatis");
        account.setAdesc("ibatis-desc");
        account.setAddress("nanjing");
        baseDao.save("_save", account);
        Account item = baseDao.findById("_findbyid", id);
        System.out.printf("[%s]\n", item);
        List<Account> items = baseDao.find("_findAll");
        for (Account acc : items) {
            System.out.printf("[aid=%s,address=%s,aname=%s,adddesc=%s]\n", item.getAid(), item.getAddress(), item.getAname(), item.getAdddesc());
        }
    }
}
