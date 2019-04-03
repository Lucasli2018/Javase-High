package com.lucas.javase.mybatis.phase01.dao;

import com.lucas.javase.mybatis.phase01.po.User;

public interface UserDao {

	User findUserById(int id);
}
