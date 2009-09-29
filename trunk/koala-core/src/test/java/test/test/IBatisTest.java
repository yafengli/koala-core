package test.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.dao.AccountIBatisDao;
import test.dao.impl.ibatis.AccountDaoImpl;
import test.model.Account;

public class IBatisTest {

    private ApplicationContext ctx = null;
    private AccountIBatisDao iadao = null;
    private long id = System.currentTimeMillis();

    public IBatisTest() {
        ctx = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-common.xml", "applicationContext-ibatis.xml"});
        iadao = (AccountIBatisDao) ctx.getBean(AccountDaoImpl.BEANNAME,
                AccountIBatisDao.class);
    }

    @Test
    public void ibatis() {
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
}
