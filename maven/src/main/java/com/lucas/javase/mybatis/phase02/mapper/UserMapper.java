package com.lucas.javase.mybatis.phase02.mapper;

import com.lucas.javase.mybatis.phase02.po.User;

public interface UserMapper {
	User findUserById(int id);
}
