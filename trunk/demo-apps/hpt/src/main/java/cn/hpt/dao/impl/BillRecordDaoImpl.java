package cn.hpt.dao.impl;

import org.koala.dao.GenericDaoJpa;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.hpt.dao.DaoBeanDefinition;
import cn.hpt.dao.IBillRecordDao;
import cn.hpt.model.BillRecord;

/**
 * Date: 2009-9-8
 * Time: 13:59:20
 *
 * @version 1.0
 * @authtor YaFengLi
 */
@Component(DaoBeanDefinition.DAO_BILL_RECORD)
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class BillRecordDaoImpl extends GenericDaoJpa<BillRecord, Long> implements IBillRecordDao {
}