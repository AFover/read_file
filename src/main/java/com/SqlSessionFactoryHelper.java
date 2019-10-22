package com;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryHelper {

    private static SqlSessionFactory sqlSessionFactory;
    private static String resource = "SqlMapConfig.xml";
    public static SqlSessionFactory getSessionFactory() throws IOException {
        if (SqlSessionFactoryHelper.sqlSessionFactory == null) {
            synchronized (SqlSessionFactoryHelper.class) {
                if (sqlSessionFactory == null) {
                    InputStream inputStream = Resources.getResourceAsStream(resource);
                    SqlSessionFactoryHelper.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                }
            }
        }
        return SqlSessionFactoryHelper.sqlSessionFactory;
    }

}
