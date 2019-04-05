package com.lucas.javase.mybatis.phase04.test;


import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lucas.javase.mybatis.phase04.annotation.*;
import com.lucas.javase.mybatis.phase04.po.*;


public class AnnotationUserMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	/**
	 * @Before注解的方法会在@Test注解的方法之前执行
	 * 
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception {
		// 指定全局配置文件路径
		String resource = "mapper/SqlMapConfig.xml";
		// 加载资源文件（全局配置文件和映射文件）
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 还有构建者模式，去创建SqlSessionFactory对象
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AnnotationUserMapper AnnotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);

		User user = AnnotationUserMapper.findUserById(1);
		System.out.println(user);
	}

	@Test
	public void testFindUserList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AnnotationUserMapper AnnotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);

		List<User> list = AnnotationUserMapper.findUserList("小明");
		System.out.println(list);
	}

	@Test
	public void testInsertUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AnnotationUserMapper AnnotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);

		User user = new User();
		user.setUsername("开课吧-2");
		user.setSex("1");
		user.setAddress("致真大厦");
		AnnotationUserMapper.insertUser(user);
		System.out.println(user.getId());
	}

	@Test
	public void testDynamicSQL() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AnnotationUserMapper AnnotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);

		UserQueryVO vo = new UserQueryVO();
		User user = new User();
		// user.setUsername("小明");
		vo.setUser(user);
		List<User> list = AnnotationUserMapper.dynamicSQL(vo);
		System.out.println(list);
	}

	@Test
	public void testFindUserByIdWithResultMap() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AnnotationUserMapper AnnotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);

		User user = AnnotationUserMapper.findUserByIdWithResultMap(1);

		System.out.println(user);
	}

	@Test
	public void testOne2One() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AnnotationUserMapper AnnotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);

		List<OrdersExt> list = AnnotationUserMapper.one2One();
		System.out.println(list);
	}

	@Test
	public void testOne2Many() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AnnotationUserMapper AnnotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);

		List<UserExt> list = AnnotationUserMapper.one2Many();

		System.out.println(list);
	}

	@Test
	public void testLazyLoading() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AnnotationUserMapper AnnotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);

		// 此处代码如果查询用户信息的话，那么这是[直接加载]
		List<OrdersExt> list = AnnotationUserMapper.lazyLoading();

		for (OrdersExt ordersExt : list) {
			// 此处代码如果查询用户信息的话，那么这是[侵入式延迟加载]
			System.out.println(ordersExt.getId());

			// 此处代码如果查询用户信息的话，那么这是[深度延迟加载]
			System.out.println(ordersExt.getUser().getId());
		}
	}

}
