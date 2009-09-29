package cn.hpt.dao.impl;

import org.koala.dao.GenericDaoJpa;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.hpt.dao.DaoBeanDefinition;
import cn.hpt.dao.ICategoryDao;
import cn.hpt.model.Category;

/**
 * Date: 2009-9-8
 * Time: 13:59:20
 *
 * @version 1.0
 * @authtor YaFengLi
 */
@Component(DaoBeanDefinition.DAO_CATEGORY)
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class CategoryDaoImpl extends GenericDaoJpa<Category, Long> implements ICategoryDao {
}