package com.lucas.javase.mybatis.phase04.xml;


import java.util.List;

import com.lucas.javase.mybatis.phase04.po.*;


public interface UserMapper {
	public User findUserById(int id) throws Exception;
	public void insertUser(User user) throws Exception;
	
	//演示延迟加载
	public List<OrdersExt> findOrdersAndUserRstMap() throws Exception;
	
	public List<User> findUserList(UserQueryVO vo) throws Exception;
}