package com.itheima.test;

import com.itheima.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by crowndint on 2018/10/29.
 */
public class RoleTest {

    @Test
    public void test1() throws IOException {

        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapperConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();

        List<Role> roleList = session.selectList("com.itheima.dao.RoleDao.findAll");
        for (Role role : roleList) {
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

}
