package com.pool;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class HikariDataSourceFactory extends UnpooledDataSourceFactory {
//    private static boolean readOnly=false;
//    private static int connectionTimeout=30000;
//    private static int idleTimeout=600000;
//    private static int maxLifetime=1800000;
//    private static int maximumPoolSize=20;
//    static  HikariDataSource hikariDataSource = new HikariDataSource();
//    static {
//        hikariDataSource.setReadOnly(readOnly);
//
//        hikariDataSource.setConnectionTimeout(connectionTimeout);
//
//        hikariDataSource.setIdleTimeout(idleTimeout);
//
//        hikariDataSource.setMaximumPoolSize(maxLifetime);
//
//        hikariDataSource.setMaximumPoolSize(maximumPoolSize);
//    }
    public HikariDataSourceFactory() {
        this.dataSource = new HikariDataSource();
    }

}
