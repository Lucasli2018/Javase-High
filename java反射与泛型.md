## java反射

通过Class实例获取class信息的方法就是反射

jvm总是动态加载class，可以在运行时根据条件控制加载class（例子就是commons-logging动态加载log4j或者jdk-logging）

### Class类

**每加载一个class，jvm就创建一个Class类型实例，并关联起来**

但是这个步骤不可见，因为构造方法私有

```java
public final class Class{
    private Class(){}
}
```

![1554562327184](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1554562327184.png)

![1554562532387](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1554562532387.png)

**获取class的Class实例3种：**

- Type.class
  - Class c1=String.class
- getClass()
  - Class c2="Hello".getClass()
- Class.forName()
  - Class c3=Class.forName("java.lang.String")

**Class实例在jvm中式唯一的**

- 可以使用==比较

```java
c1==c2==c3//true
```

**创建class实例**

- newInstance()可以调用public方法
- 可以在运行时根据条件加载不同实现类，比如log4j的加载

#### commons-logging加载log4j的原理

```java
LogFactory factory;
if(isClassPresent("org.apache.logging.log4j.Logger")){
    factory=createLog4j();
}else{
    factory=createJdkLog();
}
```

```java
boolean isClassPresent(String name){
    try{
        Class.forName(name);
        return true;
    }catch(Exception e){
        return false;
    }
}
```

#### class

class本身是一种数据类型（Type），class/interface的数据类型是Class，JVM为每个加载的class创建唯一的Class实例。

Class实例包含该class的所有信息，通过Class实例获取class信息的方法称为反射（Reflection）

**获取一个class的Class实例：**

* Class cls = String.class;
* Class cls = "str".getClass();
* Class cls = Class.forName("java.lang.String");

注意Class的==比较和`instanceof`的区别。

**从Class实例获取class信息：**

* getName()
* getSimpleName()
* getPackage()

**从Class实例判断class类型：**

* isInterface()
* isEnum()
* isArray()
* isPrimitive()

**创建class实例：**

cls.newInstance()

JVM总是动态加载class，可以在运行期根据条件控制加载class。

### Field

**通过Class实例获取字段field信息：**

* getField(name)：获取某个public的field（包括父类）
* getDeclaredField(name)：获取当前类的某个field（不包括父类）
* getFields()：获取所有public的field（包括父类）
* getDeclaredFields()：获取当前类的所有field（不包括父类）

**Field对象包含一个field的所有信息：**

* getName()
* getType()
* getModifiers()

**获取和设置field的值：**

* get(Object instance)
* set(Object instance, Object value)

通过反射访问Field需要通过SecurityManager设置的规则。

如果一个字段是private的，通过设置setAccessible(true)来访问非public字段。

setAccessible(true)可能会失败，比如方法java.lang的核心类的私有方法，因为jdk的方法禁止修改，通常第三方的类没有这个限制

### Method

和field类似

**通过Class实例获取方法Method信息：**

* getMethod(name, **Class...**)：获取某个public的method（包括父类）
* getDeclaredMethod(name, **Class...**)：获取当前类的某个method（不包括父类）
* getMethods()：获取所有public的method（包括父类）
* getDeclaredMethods()：获取当前类的所有method（不包括父类）

**Method对象包含一个method的所有信息：**

* getName()
* getReturnType()
* getParameterTypes()
* getModifiers()

**调用Method：**

* Object invoke(Object instance, **Object... args**)

通过设置setAccessible(true)来访问非public方法。

反射调用Method也遵守多态的规则。

```java
Integer n=new Integer(12);
Class cls=n.getClass();
Method m=cls.getMethod("toString");
String s=(String)m.invoke(n);
//"12"
```

使用反射调用，也会保证多态的正确性

### Constructor

**调用public无参数构造方法：**

* Class.newInstance()

通过Class实例获取Constructor信息：

* getConstructor(Class...)：获取某个public的Constructor
* getDeclaredConstructor(Class...)：获取某个Constructor
* getConstructors()：获取所有public的Constructor
* getDeclaredConstructors()：获取所有Constructor

**通过Constructor实例可以创建一个实例对象：**

* newInstance(Object… parameters)

通过设置setAccessible(true)来访问非public构造方法。

### 继承关系

**获取父类的Class：**

* ## **Class getSuperclass()**
* Object的父类是null
* interface的父类是null

**获取当前类直接实现的interface：**

* **Class[] getInterfaces()**
* 不包括间接实现的interface
* 没有interface的class返回空数组
* interface返回继承的interface

**判断一个向上转型是否成立：**

* bool isAssignableFrom(Class)

  B向上转为A

A.isAssignableFrom(B)

## 注解

### 注解

是什么？

是java语言用于工具处理的标注

可以配置参数

如果参数名称是value，可以省略参数

注解（Annotation）是放在Java源码的类、方法、字段、参数前的一种标签。

什么用？

注解本身对代码逻辑没有任何影响，如何使用注解由工具（比如编译器，写了注解编译器可以帮助检查）决定。

**编译器可以使用的注解：**

* @Override 重写（不是重载overload）
* @Deprecated（过时，作废）
* @SuppressWarnings（取消警告）

注解可以定义配置参数和默认值。

注解定义配置参数包括所有基本类型，String，枚举类型，数组等（参数必须是常量）

如果只写常量就是省略了value参数

- @check(99) -->@check(value=99)

### 定义注解

使用@interface定义注解（Annotation）。

**注解的注解就是元注解**

使用元注解定义注解：

* @Target
  * ElementType.TYPE类，接口
  * ElementType.FIELD字段
  * ElementType.METHOD方法
  * ElementType.CONSTRUCTOR构造方法
  * ElementType.PARAMETER方法参数
* @Retention
* @Repeatable
* @Inherited

定义Annotation的步骤：

1. 用@interface定义注解
2. 用元注解（meta annotation）配置注解
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Report{
    int type() default 0;
    String level() default "info";
    String value() default "";
}
```

  * Target：必须设置
  * Retention：注解生命周围，一般设置为RUNTIME，默认为SOURCE
      * RetentionPolicy.SOURCE编译器编译时直接丢弃@Override，只给编译器看，编译结束直接丢弃
  * 通常不必写@Inherited, @Repeatable等等

3. 定义注解参数和默认值

### 处理注解

如何使用注解？由工具决定

如何读取注解？使用反射，因为注解也是class

**使用反射API读取Annotation：**

* Class.isAnnotationPresent(Class)
* Field.isAnnotationPresent(Class)
* Method.isAnnotationPresent(Class)
* Constructor.isAnnotationPresent(Class)
* Class.getAnnotation(Class)
* Field.getAnnotation(Class)
* Method.getAnnotation(Class)
* Constructor.getAnnotation(Class)
* getParameterAnnotations()

**可以通过工具处理注解来实现相应的功能：**

可以在运行时通过反射读取RUNTIME类型的注解

不要漏写**@Retention(RetentionPolicy.RUNTIME)**

* 对JavaBean的属性值按规则进行检查
* JUnit会自动运行@Test标记的测试方法

### 处理注解

请根据注解：

* @NotNull：检查该属性为非null
* @Range：检查整型介于min～max，或者检查字符串长度介于min～max
* @ZipCode：检查字符串是否全部由数字构成，且长度恰好为value

实现对Java Bean的属性值检查。如果检查未通过，抛出异常。

```java
static void checkPerson(Person p) throws Exception {
		System.out.println("check " + p + "...");
		// TODO: check person...
		// @NotNull: 非null
		// @Range: 整型检查值min~max, 字符串检查长度介于min~max
		// @ZipCode(value): 检查字符串是否全部由数字构成，且长度恰好为value
		Class c = Person.class;
		for (Field f : c.getDeclaredFields()) {//注意由于字段都是priavte，所以需要使用getDeclaredFields
			try{
				f.setAccessible(true);
				checkField(f, p);
			}catch(Exception e){
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}
	}
	static void checkField(Field f, Person p) throws Exception {
		if (f.isAnnotationPresent(NotNull.class)) {
			Object r = f.get(p);
			if (r == null) {
				System.out.println("Error: field " + f.getName() + " is null.");
			}
		}
		if (f.isAnnotationPresent(Range.class)) {
			Range range = f.getAnnotation(Range.class);
			int n = (Integer) f.get(p);
			if (n < range.min() || n > range.max()) {
				//System.out.println("Error: field " + f.getName() + " is out of range.");
				throw new RangeException("Error: field " + f.getName() + " is out of range.");
			}
		}
		if (f.isAnnotationPresent(ZipCode.class)) {
			ZipCode zipCode = f.getAnnotation(ZipCode.class);
			String s = (String) f.get(p);
			try{
				Integer.parseInt(s);
			}catch(Exception e){
				System.out.println("Error: field " + f.getName() + " is not a number.");
			}
			if (s.length()!=zipCode.value()) {
				System.out.println("Error: field " + f.getName() + " is not the length.");
			}
		}
	}
```

