package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Account;
import com.itheima.domain.User;
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
public class AccountTest
{


    @Test
    public void testFindAll() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        List<Account> accounts = accountDao.findAll();
        for(Account account : accounts){
            System.out.println("--------每个account的信息------------");
            System.out.println(account);
            System.out.println("--------每个user的信息------------");
            System.out.println(account.getUser());
        }
    }

    @Test
    public void testFindAll1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println("==========每个用户的信息============");
            System.out.println(user);
            System.out.println("==========延迟获取Account==============");
            System.out.println(user.getAccounts());
        }
    }
}
