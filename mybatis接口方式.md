#### mybatis接口方式开发

##### po类User.java

```java
public class User {
	private int id;
	private String username;
	private Date birthday;
	private String sex;
	private String address;
    //getter和setter
}
```

##### dao接口

```java
public interface UserDao {

	User findUserById(int id);
}
```

##### dao实现类

```java
public class UserDaoImpl implements UserDao {

	private SqlSessionFactory sqlSessionFactory;

	// 注入sqlSessionFactory
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public User findUserById(int id) {
		// sqlsessionFactory工厂类去创建sqlsession会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// sqlsession接口，开发人员使用它对数据库进行增删改查操作
		User user = sqlSession.selectOne("test.findUserById", id);
		return user;
	}

}
```

##### 测试代码Test.java

```java
public class Test1 {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws Exception{
		// 加载全局配置文件（同时把映射文件也加载了）
		String resource = "phase01/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// sqlsessionFactory需要通过sqlsessionFactoryBuilder读取全局配置文件信息之后
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() {
		UserDao dao = new UserDaoImpl(sqlSessionFactory);
		User user = dao.findUserById(3);
		System.out.println(user);
	}

}
```



##### 配置文件

SqlMapConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<properties resource="phase01/db.properties"></properties>
	<environments default="development">
		<environment id="development">
			<transactionManager type="JDBC" />
			<dataSource type="POOLED">
				<property name="driver" value="${db.driver}" />
				<property name="url" value="${db.url}" />
				<property name="username" value="${db.username}" />
				<property name="password" value="${db.password}" />
			</dataSource>
		</environment>
	</environments>
	<mappers>
		<mapper resource="phase01/UserMapper.xml" />
	</mappers>
</configuration>
```

db.properties

```properties
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/ssm?characterEncoding=utf-8
db.username=root
db.password=root
```

UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="test">
	<!-- 注意事项： -->
	<!-- 1：如果parameterType为简单类型（基本类型+String类），#{}中的参数名称可以任意 -->
	<!-- 2：如果parameterType为POJO类型，#{}中的参数名称必须和POJO中的属性名称一致 -->
	<!-- 3：如果resultType为POJO类型，SELECT中的列名和POJO中的属性名称一致 -->
	<select id="findUserById" parameterType="int"
		resultType="com.lucas.javase.mybatis.phase01.po.User">
		SELECT * FROM user WHERE id = #{id}
	</select>
</mapper>
```





#### mybatis的Mapper方式开发

优点是不需要dao的实现类

缺点是有很多限制条件：比如mapper接口方法名称和mapper.xml的语句id必须一致

UserMapper.java

```java
public interface UserMapper {
	User findUserById(int id);
}
```

Test.java

```java
@Test
	public void testFindUserById() {
		//创建UserMapper对象
		log.info("创建UserMapper对象");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		//调用UserMapper对象的API
		log.info("调用UserMapper对象的API");
		User user = mapper.findUserById(3);
		log.info(user);
	}
```





#### mybatis注解开发

这样的方法最简单，适合简单的业务逻辑，但是复杂的逻辑，比如一对多则必须使用mapper方法开发

Test.java，调用方法和mapper还是一样的

```java
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
```

dao接口不同，mapper.xml的语句全部写到这里了

```java
public interface AnnotationUserMapper {
	@Select("SELECT * FROM user WHERE id = #{id}")
	User findUserById(int id);
	
	@Delete("delete from user where username=#{u}")
	int deleteUserByName(String username);
}
```

SqlMapConfig.xml的配置也不同，使用的不是mapper，而是package

```xml
<mappers>
    <!-- mapper开发方式 -->
    <!-- <mapper resource="phase02/UserMapper.xml" /> -->

    <!-- 注解开发方式 -->
    <package name="com.lucas.javase.mybatis.phase02.mapper"/>
</mappers>
```

