package com.lucas.javase.mybatis.phase02.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.lucas.javase.mybatis.phase02.po.User;

public interface AnnotationUserMapper {
	@Select("SELECT * FROM user WHERE id = #{id}")
	User findUserById(int id);
	
	@Delete("delete from user where username=#{u}")
	int deleteUserByName(String username);
}
