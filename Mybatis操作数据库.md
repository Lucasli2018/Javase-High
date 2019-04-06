#### Mybatis操作数据库

##### pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.lucas.javase.demo</groupId>
  <artifactId>maven</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>maven</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <java.version>1.8</java.version>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <!-- mybatis依赖 -->
	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis</artifactId>
		<version>3.4.6</version>
	</dependency>
	
	<!-- mysql依赖 -->
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>5.1.35</version>
	</dependency>

	<!-- 分页插件 -->
	<dependency>
		<groupId>com.github.pagehelper</groupId>
		<artifactId>pagehelper</artifactId>
		<version>5.1.6</version>
	</dependency>

	<!-- 日志 -->
	<dependency>
		<groupId>org.apache.logging.log4j</groupId>
		<artifactId>log4j-core</artifactId>
		<version>2.8.2</version>
	</dependency>
	<dependency>
		<groupId>org.apache.logging.log4j</groupId>
		<artifactId>log4j-jcl</artifactId>
		<version>2.8.2</version>
	</dependency>
	<dependency>
		<groupId>commons-logging</groupId>
		<artifactId>commons-logging</artifactId>
		<version>1.2</version>
	</dependency>
	<!-- 单元测试 -->
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.12</version>
		<scope>test</scope>
	</dependency>
  </dependencies>
</project>

```

db.properties

```properties
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/ssm?characterEncoding=utf-8
db.username=root
db.password=root
```

##### SqlMapConfig.xml

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

##### UserMapper.xml

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

##### MybatisDemo.java

```java
public class MybatisDemo {

	// 抽取代码，方便重用
	private SqlSessionFactory sqlSessionFactory;
	static final Log log=LogFactory.getLog(MybatisDemo.class);
	/**
	 * @Before注解的方法会在@Test注解的方法之前执行
	 * 
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception {
		log.info("============================");
		// 指定全局配置文件路径
		log.info("指定全局配置文件路径");
		String resource = "demo/SqlMapConfig.xml";
		// 加载资源文件（全局配置文件和映射文件）
		log.info("加载资源文件（全局配置文件和映射文件）");
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 使用构建者模式，去创建SqlSessionFactory对象
		log.info("使用构建者模式，去创建SqlSessionFactory对象");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 设计模式分三类23种：创建型（5）、结构型（7）、行为型（11）
	}

	@Test
	public void testFindUserById() {
		// 由SqlSessionFactory工厂去创建SqlSession（会话）
		log.info("由SqlSessionFactory工厂去创建SqlSession（会话）");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user=null;
		// 调用sqlsession接口，去实现数据库的增删改查操作
		// 参数1：statement的id值（可以不加namespace）：namespace+"."+statementID
		// 参数2：唯一入参
		log.info("调用sqlsession接口，去实现数据库的增删改查操作---->test.findUserById");
		try{
			//注意引入的包，这里有一个坑（引入了package com.lucas.javase.mybatis.phase01.po;）
			user = sqlSession.selectOne("test.findUserById", 2);
		}catch(Exception e){
			log.error(e.toString());
		}finally{
			log.info(user);
			// 释放资源
			log.info("释放资源");
			sqlSession.close();
		}
	}

}
```

