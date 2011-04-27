package org.koala.dao.mybatis;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.PersistenceExceptionTranslator;


/**
 * User: liyafeng
 * Date: 2008-3-19
 * Time: 10:13:04
 * @version 1.0
 * @author YaFengLi
 */
public class BaseMyBatisDao extends SqlSessionTemplate {

    public BaseMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    public BaseMyBatisDao(SqlSessionFactory sqlSessionFactory, ExecutorType executorType, PersistenceExceptionTranslator exceptionTranslator) {
        super(sqlSessionFactory, executorType, exceptionTranslator);
    }

    public BaseMyBatisDao(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
        super(sqlSessionFactory, executorType);
    }
}