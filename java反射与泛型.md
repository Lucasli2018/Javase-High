

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

## 泛型

#### 基本

泛型（Generic）就是定义一种模板，例如ArrayList<T>。

在代码中为用到的类创建对应的ArrayList<类型>： 
```java
ArrayList<String> strList = new ArrayList<String>();
```

编译器会针对泛型类型作检查。 
要注意泛型的继承关系。

为什么需要泛型？

jdk提供ArrayList“可变长度”数组，比使用数组方便，如果没有泛型，存储String需要强制转型，不方便易出错

注意：

- 泛型就是编写模板代码来适应任意类型
- 不必对类型进行强制转换
- 编译器将对类型进行检查
- 注意泛型的继承关系
  - ArrayList<Integer>可以向上转型为List<Integer>，但是不能转型为ArrayList<Number>，就是T类型不能变



### 使用泛型

定义泛型（Generic）类型

* List<T>的泛型接口变为强类型：
  * void add(Number)
  * Number get(int)

不指定泛型参数类型时，编译器会给出警告，且只能将<T>视为Object类型。

省略：

- 可以省略编译器能自动推断出的类型

```java
List<Number> list=new ArrayList<>();
```

使用Arrays.sort()的类型需实现Comparable接口

return 1就是触发换位

### 编写泛型

泛型（Generic）一般用在集合类中 
编写泛型时，需要定义泛型类型<T>

```java
public class Pair<T> { … }
```

静态方法不能引用泛型类型<T>，必须定义其他类型<K>来实现“泛型”：

```java
public static <K> Pair<K> create(K first, K last) { … } 
```

泛型可以同时定义多种类型<T, K>。 

### 擦拭法

java泛型的实现方式是擦拭法，在jvm内部编译器都是把泛型当做Object处理，只在需要转型的时候才强制转型。（由于这个原因，<T>不能是基本类型，因为Object无法持有基本类型）

而且泛型的class永远是同一个class

```java
Pair<String> s=...;
Pair<Integer> i=...;
s.getClass()==i.getClass();//true
```

Java的泛型（Generic）是采用擦拭法（Type Erasure）实现的。

**擦拭法的局限：**

* <T>不能是基本类型，例如int
* Object字段无法持有基本类型
* 无法取得带泛型的Class
  * Pair<String>.class
* 无法判断带泛型的Class
* 不能实例化 T 类型
  * 必须借助Class<T>

```java
public Pair(Class<T> clazz){
    first=clazz.newInstance();
    last=clazz.newInstance();
}
```

可以继承自泛型类：

```java
public class IntPair extends Pair<Integer> {
}
```

子类可以获取父类的泛型类型。

继承关系：

```java
Type
|
+- Class
|
+- ParameterizedType
|
+- GenericArrayType
|
+- WildcardType
```

获取父类型的泛型GenericHelper.java

```java
public class GenericHelper {

	public static Class<?> getParameterizedTypeOfSuperclass(Class<?> clazz) {
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			Type[] types = pt.getActualTypeArguments();
			// 假定父类只有一个泛型类型:
			return (Class<?>) types[0];
		}
		return null;
	}
}
```

### extends通配符

可以把Integer转为Number（get方法有效），但是不能把Number类型转为Integer（set方法失效）

Pair<Integer>不是Pair<Number>的子类，如果一个方法需要接受Integer和Number，可以使用extends关键字

<? extends Number>和<T extends Number>

<? extends Number>？可以是Number和NUmber的子类

<T extends Number>限定是Number和NUmber的子类

```
void someMethod(List<? extends Number> list) {
    Number n = list.get(0);
    list.add(n); // ERROR
}
```

允许传入`List<Number>`，`List<Integer>`，`List<Double>`...

允许调用方法获取Number类型

不允许调用方法传入Number类型（null除外）

定义泛型时可以通过extends限定T必须是Number或Number的子类

### super通配符

不能把Integer的父类Number转为Integer，所以get方法失效

<? super Integer> 通配符允许调用set方法传入Integer引用，不能调用get方法获取Integer引用。

```
void someMethod(List<? super Integer> list) {
    list.add(123);
    Integer n = list.get(0); // ERROR
}
```

允许传入`List<Integer>`，`List<Number>`，`List<Object>`

允许调用方法传入Integer类型

不允许调用方法获取Integer类型（Object除外）

定义泛型时可以通过extends限定T必须是Integer或Integer的超类

**extends和super通配符的区别**

&lt;? extends T&gt;允许调用方法获取T的引用

&lt;? super T&gt;允许调用方法传入T的引用

```java
public class Collections{
    public static<T> void copy(List<? super T>dest,List<? extends T>src){
        for(int i=0;i<src.size();i++){
            T t=src.get(i);
            dest.add(t);
        }
    }
}
```

只能获取Object引用

只能传入null

可以用&lt;T&gt;消除&lt;?&gt;

### 泛型反射

Class<T>

@SafeVarargs消除编译警告

不能直接创建T[] ，擦拭后编程Object[]

 * Class<T>   
 * Constructor<T>

可以声明带泛型的数组，但不能直接创建带泛型的数组，必须强制转型

可以通过`Array.newInstance(Class<T>, int)`创建`T[]`数组，需要强制转型

## Java集合

java.util提供了集合类，包括：

* Collection：根接口
* List：有序列表接口
* Set：无重复元素集合接口
* Map：通过Key查找Value的映射表接口

Java集合支持范型，通过迭代器（Iterator）访问集合。

jdk遗留类，不建议使用：

- Stack：基于Vector实现的LIFO栈
- Hashtable：线程安全的Map实现
- Vector：线程安全的List实现

遗留接口，不应该继续使用：

- Enumeration<T>：已被Iterator<T>取代

Iterator遍历集合

### List遍历

List是一种有序列表，通过索引访问元素。

数组也是有序结构，但是大小固定，删除元素需要移动后面元素

ArrayList<E>：内部使用数组存储所有元素，查找速度快，内存占用少，

LinkedList<E>：查找速度慢，指定位置添加删除快，内存占用多

* void add(E e) 在末尾添加一个元素
* void add(int index, E e) 在指定索引添加一个元素
* int remove(int index) 删除指定索引的元素
* int remove(Object e) 删除某个元素
* E get(int index) 获取指定索引的元素
* int size() 获取链表大小（包含元素的个数）

List有ArrayList和LinkedList两种实现。

遍历List使用Iterator或者foreach循环。

List和Array可以相互转换。

遍历一个List<E>：

- 使用get（int index）

- 使用Iterator<E>

  - ```java
    for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
    			System.out.println(iterator.next());
    		}
    ```

    

- 使用foreach：编译器会自动处理成Iterator

Array和List的转化

```java
//List 转Array
		String[] arr=list.toArray(new String[list.size()]);
		for (String string : arr) {
			System.out.println(string);
		}
```

```java
//Array 转List
		List<String> list2=Arrays.asList(arr);	
		System.out.println(list2);
```

### List比较

list是有序链表，每个元素可以通过索引确定位置

```java
int indexOf(Object o)
```

判断元素是否存在或者查找元素索引：

* boolean contains(Object o) 是否包含某个元素
* int indexOf(Object o) 查找某个元素的索引，不存在返回-1

要正确调用contains / indexOf方法，放入的实例要正确实现equals()

equals()编写方法：

1. 判断`this==o`
2. 判断`o instanceof Person`
3. 强制转型，并比较每个对应的字段
  * 基本类型字段用==直接比较
  * 引用类型字段借助`Objects.equals()`判断

eclipse自动生成的equals方法

```java
@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
        //可以使用Objects.equals(this.name,obj.name)来简化
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
```

hashCode方法

```java
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
```

如果不在list中查找元素，不必重写equals方法

### Map接口

Map是一种键值映射表，可以通过Key快速查找Value

常用方法：

* V put(K key, V value)：把Key-Value放入Map
* V get(K key)：通过Key获取Value
* boolean containsKey(K key)：判断Key是否存在

**遍历Map：**

用for...each循环：

* 循环Key：keySet()
* 循环Key和Value：entrySet()

常用的实现类：

* HashMap：不保证有序
* SortedMap：保证按Key排序，实现类有TreeMap

继承树

![1554748347186](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1554748347186.png)

```java
Map<String, Person> map = new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
```

```java
//keySet遍历
		for (String key : map.keySet()) {
			System.out.println(map.get(key));//HashMap输出顺序不定
		}
		System.out.println();
		//entrySet遍历
		for (Entry<String, Person> p : map.entrySet()) {
			System.out.println(p.getKey()+"--->"+p.getValue());
		}
```

需要排序使用TreeMap，通常使用HashMap

### equals和hashCode

利用Objects.equals和Objects.hashCode方法

正确使用Map必须保证：

* 作为Key的对象必须正确覆写equals()方法 
* 作为Key的对象必须正确覆写hashCode()方法

hashCode实际上是一个int值

```java
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
```

覆写hashCode：
 * 如果两个对象相等，则两个对象的hashCode()必须相等
* 如果两个对象不相等，则两个对象的hashCode()尽量不相等（可以相等，会造成效率下降）

hashCode可以通过Objects.hashCode()辅助方法实现

### 使用Properties

Properties用于读取配置

* .properties文件只能使用ASCII编码 （很坑爹）
*  可以从文件系统和ClassPath读取
* 读取多个.properties文件，后读取的Key-Value会覆盖已读取的Key-Value

Properties实际上是从Hashtable派生，但只需调用`getProperty`和`setProperty`

Hashtable是线程安全的Map实现

复制文件的全路径：右键-->copy qualified name

![1554749885771](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1554749885771.png)

classpath读取路径

```java
Properties props = new Properties();
//load方法读取classpath
props.load(Main.class.getResourceAsStream("/com/lucas/javase/collection/Map/properties/setting.properties"));
//设置默认值
props.getProperty("course.description","default value");
```

### Set接口

Set用于存储不重复的元素集合：

* boolean add(E e)
* boolean remove(Object o)
* boolean contains(Object o)
* int size()

利用Set可以去除重复元素

放入Set的元素要正确实现equals()和hashCode()

Set不保证有序：

* HashSet是无序的
* TreeSet是有序的

实现了SortedSet接口的是有序Set

排序

```java
Set<String> set = new TreeSet<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return -o1.compareTo(o2);
			}
		});
		set.addAll(list);
```

请将List的元素去重，但保留元素在List中的原始顺序，即：

["abc", "xyz", "abc", "www", "edu", "www", "abc"]

去重时应该删除：

["abc", "xyz", ~~"abc"~~, "www", "edu", ~~"www"~~, ~~"abc"~~]

去重后的结果应该为：

["abc", "xyz", "www", "edu"]

提示：LinkedHashSet

```java
// FIXME:请将List的元素去重，但保留元素在List中的原始顺序
	static List<String> removeDuplicateButKeepOriginOrder(List<String> list) {
		Set<String> set = new LinkedHashSet<>(list);
		return new ArrayList<String>(set);
	}
```

### Queue接口

队列（Queue）是一种先进先出（FIFO）的数据结构

实现类：ArrayDeque，LinkedList

操作Queue的元素的方法：
 * 添加至队尾压栈：add() / offer() 
 * 获取队列头部元素并删除：E remove() / E poll()
* 获取队列头部元素但不删除：E element() / E peek()

两组方法的区别：是否抛出Exception

避免把null添加到队列

remove方法可能报异常

```java
java.util.NoSuchElementException
```

### PriorityQueue

继承树

PriorityQueue<E>直接继承AbstractQueue<E>

间接继承AbstractCollection<E>，实现Queue<E>

PriorityQueue的出队顺序与元素的优先级有关：

从队首获取元素时，总是获取优先级最高的元素

默认按元素比较的顺序排序（必须实现Comparable接口）

可以通过Comparator自定义排序算法（不必实现Comparable接口）

### Deque

扩展（继承）Queue接口

Deque实现一个双端队列（Double Ended Queue）：

* 既可以添加到队尾，也可以添加到队首
* 既可以从队首获取，又可以从队尾获取

* 添加元素到队尾：addLast(E e) / offerLast(E e)
* 取队首元素并删除：E removeFirst() / E pollFirst()
* 取队首元素但不删除：E getFirst() / E peekFirst()

总是调用xxxFirst / xxxLast以便与Queue的方法区分开

实现类：ArrayDeque，LinkedList

**面向抽象编程原则：尽量持有接口，而不是实现类**

避免把null添加到队列？为什么

### Stack

不要用遗留类stack，而是

**可以使用Deque实现Stack功能**

栈（Stack）是一种后进先出（LIFO）的数据结构

操作栈的元素的方法：
 * push(E e)：压栈 * pop()：出栈 * peek()：取栈顶元素但不出栈

Java使用Deque实现栈的功能，注意只调用push/pop/peek，避免调用Deque的其他方法

应用广泛：加减乘除计算