package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by crowndint on 2018/10/26.
 */
public class AppTest {

    @Test
    public void testFindAll() throws IOException {

        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapperConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        //UserDao userDao = session.getMapper(UserDao.class);
        //5.使用代理对象执行方法
        //List<User> users = userDao.findAll();

        List<User> users = session.selectList("com.itheima.dao.UserDao.findAll");
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
            System.out.println(user.getRoleList());
            System.out.println("--------------------------");
        }
        //6.释放资源
        session.close();
        in.close();

    }


    @Test
    public void testAdd() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User.Builder().setUsername("小泽").setSex("女").setAddress("日本")
                .setBirthday(new Date()).build();
        sqlSession.insert("com.itheima.dao.UserDao.add", user);

        System.out.println(user.getId() + "   " + user.getSex() + "   " + user.getAddress());

//        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        userDao.add(user);

        sqlSession.commit();
    }

    @Test
    public void testUpdate() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User.Builder().setUsername("小室友里")
                .setBirthday(new Date()).setId(104).build();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.update(user);
        //sqlSession.update("com.itheima.dao.UserDao.update", /*user*/queryVo);
        sqlSession.commit();
    }


    @Test
    public void testDelete() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.delete(114);
        //User user = new User.Builder().setId(109).build();
        //sqlSession.delete("com.itheima.dao.UserDao.delete", 108);
        sqlSession.commit();
    }

    @Test
    public void testFindUserById() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findById(104);

        //User user = sqlSession.selectOne("com.itheima.dao.UserDao.findUserById", 107);
        System.out.println(user);

        sqlSession.commit();
    }

    @Test
    public void testFindByUsername() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findUserByUserName("小");
        //List<User> users = sqlSession.selectList("com.itheima.dao.UserDao.findUserByUserName", "小");
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.commit();
    }

    @Test
    public void testFindTotal() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int total = sqlSession.selectOne("com.itheima.dao.UserDao.findTotal");
        System.out.println(total);
        sqlSession.commit();
    }

    @Test
    public void testFindUserByCondition() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User.Builder().setUsername("%小%")
                .build();
        List<User> userList = userDao.findUserByCondition(user);
        for (User u : userList) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindByIds() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Map<String, List> map = new HashMap<String, List>();
        map.put("ids", Arrays.asList(41, 105, 106));
        List<User> userList =  sqlSession.selectList("com.itheima.dao.UserDao.findByIds", map);
        for (User u : userList) {
            System.out.println(u);
            System.out.println(u.getAccounts());
            System.out.println(u.getRoleList());
        }
    }






}
