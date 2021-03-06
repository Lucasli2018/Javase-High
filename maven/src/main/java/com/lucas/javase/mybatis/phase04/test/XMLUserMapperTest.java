package com.lucas.javase.mybatis.phase04.test;


import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lucas.javase.mybatis.phase04.po.*;
import com.lucas.javase.mybatis.phase04.xml.*;


/**
 * 基于XML的mapper代理开发方式
 * 
 * @author think
 *
 */
public class XMLUserMapperTest {

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

	// 和Spring整合之后，需要再IoC容器中去管理两个对象：
	// SqlSessionFactory对象/MapperFactoryBean/MapperScannerConfigurer
	@Test
	public void testFindUserById() throws Exception {
		// 构造UserMapper对象（sqlSession）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 需要传的参数就是被代理的Mapper接口
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 调用UserMapper对象的findUserById
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}

	@Test
	public void testInsertUser() {
		fail("Not yet implemented");
	}

	// 演示延迟加载
	@Test
	public void testLazyLoading() throws Exception {
		// 构造UserMapper对象（sqlSession）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 需要传的参数就是被代理的Mapper接口
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 该行代码是否加载用户信息？直接加载会、侵入式延迟加载和深度延迟加载不会
		List<OrdersExt> list = userMapper.findOrdersAndUserRstMap();
		for (OrdersExt ordersExt : list) {
			// 侵入式延迟加载会去查询用户信息\深度延迟不会
			System.out.println(ordersExt.getNumber());
			// 深度延迟加载会去查询用户信息
			System.out.println(ordersExt.getUser().getId());
		}
	}

	@Test
	public void testFindUserList() throws Exception {
		// 构造UserMapper对象（sqlSession）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 需要传的参数就是被代理的Mapper接口
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		UserQueryVO vo = new UserQueryVO();

		User user = new User();
		user.setUsername("kkb-liuyang");
		user.setSex("1");

		List<Integer> idList = new ArrayList<Integer>();
		idList.add(1);
		idList.add(10);
		idList.add(16);
		vo.setIdList(idList);

		vo.setUser(user);
		List<User> list = userMapper.findUserList(vo);
		System.out.println(list);
	}

	/*
	 * @Test public void testOneLevelCache() throws Exception{ SqlSession sqlSession
	 * = sqlSessionFactory.openSession(); UserMapper mapper =
	 * sqlSession.getMapper(UserMapper.class); // 第一次查询ID为1的用户，去缓存找，找不到就去查找数据库 User
	 * user1 = mapper.findUserById(1); System.out.println(user1);
	 * 
	 * // 第二次查询ID为1的用户 User User = mapper.findUserById(1);
	 * System.out.println(User);
	 * 
	 * sqlSession.close(); }
	 */

	@Test
	public void testOneLevelCache() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		// 第一次查询ID为1的用户，去缓存找，找不到就去查找数据库
		User user1 = mapper.findUserById(1);
		System.out.println(user1);

		User user = new User();
		user.setUsername("隔壁老詹1");
		user.setAddress("洛杉矶湖人");
		// 执行增删改操作，清空缓存
		mapper.insertUser(user);

		// 第二次查询ID为1的用户
		User User = mapper.findUserById(1);
		System.out.println(User);

		sqlSession.close();
	}

	@Test
	public void testTwoLevelCache() throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();

		UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
		UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
		// 第一次查询ID为1的用户，去缓存找，找不到就去查找数据库
		User user1 = mapper1.findUserById(1);
		System.out.println(user1);
		// 关闭SqlSession1
		sqlSession1.close();

		// 第二次查询ID为1的用户
		User User = mapper2.findUserById(1);
		System.out.println(User);
		// 关闭SqlSession2
		sqlSession2.close();
	}

}
