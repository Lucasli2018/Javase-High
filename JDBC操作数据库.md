#### JDBC操作数据库

##### pom.xml

``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <java.version>1.8</java.version>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
	<dependencies>
        <!-- mysql依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.35</version>
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

##### log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
	<Properties>
	<!-- 打印日志的格式 -->
		<Property name="log.pattern">%d{MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36}%n%msg%n%n</Property>
		<Property name="file.all.filename">log/all.log</Property>
		<Property name="file.all.pattern">log/all.%i.log.gz</Property>
	</Properties>
	<Appenders>
		<Console name="stdout">
			<PatternLayout pattern="%d %p [%t]: %m%n" />
		</Console>
		<RollingFile name="all" bufferedIO="true" fileName="log/all.log"
			filePattern="log/all.%i.log.gz">
			<PatternLayout pattern="%d{MM-dd HH:mm:ss.SSS} %-5level [%t]: %logger{36}%n%msg%n%n" />
			<!-- 这里Polices的意思是每当文件大小达到1mb时，切割成新的文件 -->
			<Policies>
				<SizeBasedTriggeringPolicy size="1 MB" />
			</Policies>
			<DefaultRolloverStrategy max="10" />
		</RollingFile>
	</Appenders>
	
	<Loggers>
		<Root level="info">
			<AppenderRef ref="stdout" />
			<!-- 这里必须绑定一个Appender否则无法输出 -->
			<AppenderRef ref="all" level="info" />
		</Root>
	</Loggers>
</Configuration>

```

##### JdbcDemo.java

```java
public class JdbcDemo {

	static final String DRIVER="jdbc:mysql://localhost:3306/ssm?characterEncoding=utf-8";
	static final String USERNAME="root";
	static final String PASSWORD="root";
	static final Log log=LogFactory.getLog(JdbcDemo.class);
	
	@Test
	public void test() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// 加载数据库驱动
			log.info("加载数据库驱动");
			Class.forName("com.mysql.jdbc.Driver");

			// 通过驱动管理类获取数据库链接connection = DriverManager
			log.info("通过驱动管理类获取数据库链接connection = DriverManager");
			connection = DriverManager.getConnection(DRIVER,USERNAME,PASSWORD);

			// connection.setAutoCommit(false);

			// 定义sql语句 ?表示占位符
			log.info("定义sql语句 ?表示占位符");
			//String sql = "select * from user where username = ?";
			String sql = "select * from user";
			// 获取预处理 statement
			log.info("获取预处理 statement");
			preparedStatement = connection.prepareStatement(sql);

			// 设置参数，第一个参数为 sql 语句中参数的序号（从 1 开始），第二个参数为设置的值
			log.info("设置参数，第一个参数为 sql 语句中参数的序号（从 1 开始），第二个参数为设置的值");
			//preparedStatement.setString(1, "lucas");
			// 向数据库发出 sql 执行查询，查询出结果集
			log.info("向数据库发出 sql 执行查询，查询出结果集");
			resultSet = preparedStatement.executeQuery();
			// 遍历查询结果集
			log.info("遍历查询结果集resultSet.next()");
			while (resultSet.next()) {
				String r=resultSet.getString("id") + " " + resultSet.getString("username")+" "+resultSet.getDate("birthday");
				log.info(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			log.info("释放资源");
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block e.printStackTrace();
				}
			}
		}

	}
}
```

##### 数据库

![1554559740220](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1554559740220.png)

