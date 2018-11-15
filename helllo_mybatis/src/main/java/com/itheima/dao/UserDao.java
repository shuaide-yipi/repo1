package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by crowndint on 2018/10/26.
 */
public interface UserDao {

    //@Select("SELECT * from user")
    List<User> findAll();

    void add(User user);

    void update(User user);

    void delete(int id);

    User findById(int id);

    List<User> findUserByUserName(String username);

    List<User> findUserByCondition(User user);
}
