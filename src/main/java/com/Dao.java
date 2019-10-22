package com;

import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Dao {

    public Integer getData(Map<String,Object> map){
        SqlSession sqlSession=null;
        try {
            sqlSession=SqlSessionFactoryHelper.getSessionFactory().openSession();
            return sqlSession.selectOne("getdata",map);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }

        }
        return null;
    }

    public void upData(Map<String,Object> map){
        SqlSession sqlSession = null;
        try {
            sqlSession =SqlSessionFactoryHelper.getSessionFactory().openSession();
            sqlSession.update("updata",map);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }

        }
    }
}
