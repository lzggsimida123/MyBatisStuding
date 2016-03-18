package cn.lz.mybatis.util;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lizhaoz on 2016/3/18.
 */

public class MyBatisSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;
    public static SqlSessionFactory getSqlSessionFactory(){
        if (sqlSessionFactory==null){
            synchronized (MyBatisSqlSessionFactory.class){
                if (sqlSessionFactory==null){
                    InputStream inputStream;
                    try {
                        inputStream= Resources.getResourceAsStream("mybatis-config.xml");
                        sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
                    } catch (IOException e) {
                        throw new RuntimeException(e.getCause());
                    }
                }
            }
        }
        return sqlSessionFactory;
    }
    public static SqlSession openSession(){
        return getSqlSessionFactory().openSession();
    }
}
