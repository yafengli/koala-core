package com.greatbit.jdbc.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class SimpleMapperTemplate extends GenericMapperTemplate {
    private static SqlSessionFactory sqlSessionFactory = null;

    public Reader reader;

    public String namespace;


    public SimpleMapperTemplate(Reader reader, String namesapce) {
        this.reader = reader;
        this.namespace = namesapce;
    }

    public SimpleMapperTemplate(String resource, String namesapce) {
        try {
            this.reader = Resources.getResourceAsReader(resource);
            this.namespace = namesapce;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SimpleMapperTemplate(ClassLoader classLoader, String resource, String namesapce) {
        try {
            this.reader = Resources.getResourceAsReader(classLoader, resource);
            this.namespace = namesapce;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public SqlSessionFactory getsSqlSessionFactory() {
        try {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }
}
