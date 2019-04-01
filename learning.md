#### 面向对象练习

请定义class City，该class具有如下字段:

* name: 名称，String类型
* latitude: 纬度，double类型
* longitude: 经度，double类型

然后实例化几个City并赋值，打印。

例如：

```
City beijing = new City();
beijing.name = "Beijing";
beijing.latitude = 39.903;
beijing.longitude = 116.401;
System.out.println("xxx");
```

#### 方法

- 外部代码不可以访问private字段
- 通过public方法间接设置和获取private字段
- public方法封装了数据访问
- 通过方法访问字段更安全
- 方法返回值通过return语句实现
- 没有返回值（void）可以省略return；
- 方法内部使用隐式变量