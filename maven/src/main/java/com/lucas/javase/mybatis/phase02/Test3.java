package com.lucas.javase.mybatis.phase02;


import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lucas.javase.mybatis.phase02.mapper.AnnotationUserMapper;
import com.lucas.javase.mybatis.phase02.mapper.UserMapper;
import com.lucas.javase.mybatis.phase02.po.User;


/**
 * 测试基础应用案例
 * 
 * @author think
 *
 */
public class Test3 {
	private SqlSessionFactory sqlSessionFactory;
	static final Log log=LogFactory.getLog(Test3.class);

	@Before
	public void init() throws Exception {
		// 加载全局配置文件（同时把映射文件也加载了）
		log.info("加载全局配置文件（同时把映射文件也加载了）");
		String resource = "phase02/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// sqlsessionFactory需要通过sqlsessionFactoryBuilder读取全局配置文件信息之后
		log.info("sqlsessionFactory需要通过sqlsessionFactoryBuilder读取全局配置文件信息之后");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() {
		SqlSession sqlSession=null;
		try{
			//创建UserMapper对象
			log.info("创建UserMapper对象");
			sqlSession = sqlSessionFactory.openSession();
			AnnotationUserMapper mapper = sqlSession.getMapper(AnnotationUserMapper.class);
			//调用UserMapper对象的API
			log.info("调用UserMapper对象的API");
			User user = mapper.findUserById(3);
			log.info(user);
		}catch(Exception e){
			log.error("Exception",e);
		}finally{
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeleteUserByName() {
		SqlSession sqlSession=null;
		try{
			//创建UserMapper对象
			log.info("创建UserMapper对象");
			sqlSession = sqlSessionFactory.openSession();
			AnnotationUserMapper mapper = sqlSession.getMapper(AnnotationUserMapper.class);
			//调用UserMapper对象的API
			log.info("调用UserMapper对象的API");
			int r=mapper.deleteUserByName("peter");
			//注意：一定要commit
			sqlSession.commit();
			log.info("删除数据数量:"+r);
		}catch(Exception e){
			log.error("Exception",e);
		}finally{
			sqlSession.close();
		}
	}

}
