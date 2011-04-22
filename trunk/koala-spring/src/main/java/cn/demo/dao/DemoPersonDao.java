/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.demo.dao;

import cn.demo.pojo.DemoPerson;
import org.koala.dao.IGenericJDBCDao;

/**
 *
 * @author Administrator
 */
public interface DemoPersonDao extends IGenericJDBCDao<DemoPerson, Long> {
}
