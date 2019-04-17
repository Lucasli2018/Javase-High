### Maven介绍

什么是maven？

依赖管理工具，使用maven可以管理项目的依赖的版本，避免版本不兼容问题

配置环境，编译打包流程，版本管理，命令行工具

作用？

maven管理java项目，标准化项目结构，构建流程（编译，打包，发布），依赖管理。

- Maven是一个Java项目管理和构建工具

- Maven使用pom.xml定义项目内容，并使用预设的目录结构

- 在Maven中声明一个依赖项可以自动下载并导入classpath

- Maven使用groupId，artifactId和version唯一定位一个jar包

#### 新建maven项目

file--》new--》other--maven project

![1555512319810](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555512319810.png)

### 依赖管理

Maven通过解析依赖关系确定项目所需的jar包

常用的4种scope：

* compile(默认)
* test
* runtime
* provided

Maven从中央仓库下载所需jar包并缓存在本地

可以通过镜像加速下载

#### aliyun镜像仓库配置：

在c盘，.m2文件夹新建一个settings.xml文件

添加一下代码：

![1555513842899](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555513842899.png)

```xml
<settings>
  <mirrors>
    <mirror>
      <id>aliyun</id>
      <name>aliyun</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
  </mirrors>
</settings>
```

### 构建流程

理解Maven的概念：Lifecycle，Phase和Goal：

使用Maven构建项目就是执行Lifecycle

执行Lifecycle就是按顺序执行一系列Phase

每执行一个Phase，都会执行该Phase绑定的若干Goal

Goal是最小执行任务单元

常用的命令：mvn clean package（不会打包依赖的jar）

在target文件夹下生成一个可执行的jar

![1555514122091](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555514122091.png)

运行

```shell
java -cp ./mavendemo-0.0.1-SNAPSHOT.jar com.lucas.javase.mavendemo.App
```

报错是因为没有jar包依赖

![1555514420837](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555514420837.png)

### 使用插件

maven通过调用plugin插件来构建项目

比如mvn compile调用compiler插件执行compiler:compile这个Goal（最小执行单元）

Maven通过自定义插件可以执行项目构建时需要的额外功能

在pom.xml中声明插件及配置

插件会在某个Phase被执行

插件的配置和用法需参考插件官方文档

常用插件：

* maven-shade-plugin：打包依赖
  * 注意需要执行main-class
* cobertura-maven-plugin
* findbugs-maven-plugin

#### 打包依赖

浏览器搜索：maven shade plugin executable jar

打开网页,复制build标签内容到pom，修改mainclass，重打包

[maven-shade-plugin](http://maven.apache.org/plugins/maven-shade-plugin/examples/executable-jar.html)

```xml
<project>
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.2.1</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <transformers>
                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                  <mainClass>org.sonatype.haven.HavenCli</mainClass>
                </transformer>
              </transformers>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
  ...
</project>
```

![1555514973694](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555514973694.png)

#### 测试报告

http://www.mojohaus.org/cobertura-maven-plugin/usage.html

```xml
<project>
  ...  
  <reporting>
    <plugins>
      ...
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>cobertura-maven-plugin</artifactId>
        <version>2.7</version>
      </plugin>
    </plugins>
  </reporting>
</project>
```

可以查看代码的覆盖率

\target\site\cobertura\index.html

![1555515727076](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555515727076.png)

![1555515699973](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555515699973.png)