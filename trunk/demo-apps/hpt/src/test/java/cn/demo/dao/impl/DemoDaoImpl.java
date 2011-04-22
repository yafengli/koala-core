package cn.demo.dao.impl;

import org.koala.dao.GenericDaoJpa;
import org.springframework.stereotype.Component;

import cn.demo.dao.IDemoDao;
import cn.demo.model.Demo;

/**
 * Date: 2009-9-7
 * Time: 16:29:31
 *
 * @version 1.0
 * @authtor YaFengLi
 */
@Component("demoDao")
public class DemoDaoImpl extends GenericDaoJpa<Demo, Long> implements IDemoDao { 
}
