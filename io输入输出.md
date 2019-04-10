### IO抽象类

IO: Input / Output

IO流是一种流式的数据输入/输出模型：

* 二进制数据以byte为最小单位在InputStream / OutputStream中单向流动
* 字符数据以char为最小单位在Reader / Writer中单向流动
* JDK的java.io包提供了同步IO功能
* JDK的java.nio包提供了异步IO功能

Java的IO流的接口：

 * 字节流接口：InputStream / OutputStream
* 字符流接口：Reader / Writer

### File类

file对象可以操作文件的读写和状态

java.io.File表示文件系统的一个文件或者目录：

* isFile()：是否是文件
* isDirectory()：是否是目录

**创建File对象本身不涉及IO操作**

获取路径／绝对路径／规范路径： getPath() / getAbsolutePath() / getCanonicalPath()

```java
	public static void main(String[] args) throws IOException {
		File win = new File("C:\\Windows");
		System.out.println(win.isDirectory()); // true
		File notepad = new File("C:\\Windows\\notepad.exe");
		System.out.println(notepad.isFile()); // true
		File dir = new File("C:\\abc\\xyz");
		System.out.println(dir.mkdirs()); // -> mkdirs 如果路径不存在，自动创建一个
		File readme = new File("./src/readme.txt");
		System.out.println(readme.isFile()); // false
		System.out.println(readme.getAbsolutePath());//D:\github\Javase-High\IO输入输出\.\src\readme.txt
		System.out.println(readme.getCanonicalPath());//D:\github\Javase-High\IO输入输出\src\readme.txt
	}
```

**文件操作：**

* canRead()：是否允许读取该文件
* canWrite()：是否允许写入该文件
* canExecute()：是否允许运行该文件
* length()：获取文件大小
* createNewFile()：创建一个新文件
* static createTempFile()：创建一个临时文件
* delete()：删除该文件
* deleteOnExit()：在JVM退出时删除该文件

**目录操作：**

* String[] list()：列出目录下的文件和子目录名
* File[] listFiles()：列出目录下的文件和子目录名
* File[] listFiles(FileFilter filter)
* File[] listFiles(FilenameFilter filter)
* mkdir()：创建该目录
* mkdirs()：创建该目录，并在必要时将不存在的父目录也创建出来
* delete()：删除该目录

请编写一个Java程序，能列出当前目录下的所有子目录和文件，并按层次打印。

例如：

输出：

```java
IOFilePractice
 .classpath
 .project
 bin
  com
   feiyangedu
    sample
     Main.class
 src
  com
   feiyangedu
    sample
     Main.java
```

如果不指定参数，则使用当前目录，如果指定参数，则使用指定目录。

例如：

在Eclipse中设置命令行参数 C:\Users

输出：

```java
Users
 public
 Michael
  Desktop
   download.txt
  Documents
  Music
```

实现代码：

```java
public static void getFileName(File file, int count) {
		for (File f : file.listFiles()) { // 遍历目录
			String preStr = "";
			for (int i = 0; i <= count; i++) { // 所在层级的输出格式
				preStr += " ";
			}
			
			if (f.isDirectory()) { // 是否为目录
				System.out.println(preStr + f.getName());
				count++;
				getFileName(f, count); // 递归调用
			}else{
				System.out.println(preStr + f.getName());
			}

		}
	}
```

### InputStream抽象类

InputStream是所有输入流的超类：

* int read()读取一个字节
  * 读取下一个字节，返回字节（0-255）
  * 如果读取到末尾，返回-1
* int read(byte[])读取若干字节并填充到byte[]数组

read()方法是阻塞（blocking）的

使用try(resource)可以保证InputStream正确关闭

autoclose语法

```java
try (InputStream input = new FileInputStream("readme.txt")) {
    int n;
    while ((n = input.read()) != -1) {
        System.out.println(n);
    }
}
```

常用InputStream：

* FileInputStream
* ByteArrayInputStream
  * 模拟内存的数据，测试时使用

### OutputStream抽象类

OutputStream是所有输出流的超类：

* abstract write(int b)写入一个字节
* void write(byte[])写入byte[]数组的所有字节
* void close关闭资源（会自动执行flush方法）
* void flush()方法将缓冲器内容输出

write()方法是阻塞（blocking）的，就是程序会等待write方法执行完毕

使用try(resource)可以保证OutputStream正确关闭

常用OutputStream：

* FileOutputStream
* ByteArrayOutputStream

### 文件复制

```java
static void copy(String src, String dest) throws FileNotFoundException, IOException {
		// TODO: 将src复制为dest
		System.out.println("将"+src+"复制为"+dest+"");
		try(InputStream inputStream=new FileInputStream(src)){
			try(OutputStream outputStream=new FileOutputStream(dest)){
				int n;
				byte[] buffer=new byte[1024];
				while((n = inputStream.read(buffer)) != -1){
					outputStream.write(buffer);
					outputStream.flush();
				}
			}
		}
		System.out.println("ok");
	}
```

### Filter模式

叫做**过滤器模式**，**装饰器模式**

是一种设计模式

Filter模式是为了解决子类数量爆炸的问题

![1554826920235](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1554826920235.png)

jdk提供的InputStream：

- FileInputStream:从文件读取数据
- ServletInputStream：从HTTP请求读取数据
- Socket.getInputStream()：从TCP连接读取数据



**直接提供数据的InputStream：**

* FileInputStream
* ByteArrayInputStream
* ServletInputStream

**提供附加功能的InputStream**

从FilterInputStream派生：

* BufferedInputStream：添加缓冲功能
* DigestInputStream：添加计算签名功能
* CipherInputStream：添加加密解密功能
* GZIPInputStream：添加压缩功能

但是必须有一个是真实文件类

![1554827071491](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1554827071491.png)

Filter模式又称Decorator模式，**通过少量的类实现了各种功能的组合**

![1554827174454](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1554827174454.png)

FilterOutputStream和FilterInputStream类似

Filter模式可以在运行时动态增加功能

#### 包装类

代码：

```java
try (InputStream input = new GZIPInputStream(new BufferedInputStream(new FileInputStream("test.txt.gz")))) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int n;
			while ((n = input.read(buffer)) != -1) {
				output.write(buffer, 0, n);
			}
			byte[] data = output.toByteArray();
			String text = new String(data, "UTF-8");
			System.out.println(text);
			output.close();
		}
```

### Reader抽象类

区别：

- 字符流，以char为单位，而不是byte为单位
- 读取到字符数组char[]

Reader以字符为最小单位实现了字符流输入：

* int read() 读取下一个字符
* int read(char[]) 读取若干字符并填充到char[]数组
* void close:由于read内部已经调用inputStream的close方法，所以不能在调用

常用Reader类：

* FileReader：从文件读取，实现文件字符流输入
* CharArrayReader：从char[]数组读取，在内存中模拟一个字符流输入

Reader是基于InputStream构造的，任何InputStream都可指定编码并转换为Reader：

```java
Reader reader = new InputStreamReader(input, "UTF-8")
```

**try-resource语法是jdk1.7之后提供的**

### Writer抽象类

Writer以字符为最小单位实现了字符流输出：

* write(int c) 写入下一个字符
* write(char[]) 写入char[]数组的所有字符

常用Writer类：

* FileWriter：写入文件
* CharArrayWriter：写入char[]数组

Writer是基于OutputStream构造的，任何OutputStream都可指定编码并转换为Writer：

```java
Writer writer = new OutputStreamWriter(output, "UTF-8")
```

### Reader/Writer练习

请编写一个程序，接收两个命令行参数，分别表示源文件和目标文件，
然后用Reader／Writer把GBK编码的源文件转换为UTF-8编码的目标文件。

```java
static void copyGbkToUtf8(String src, String dest) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		// TODO: 将GBK编码的src复制为UTF-8编码的dest
		System.out.println("将GBK编码的"+src+"复制为UTF-8编码的"+dest+"");
		try (Reader reader =new InputStreamReader( new FileInputStream(src),"GBK")) {
			try (Writer writer =new OutputStreamWriter(new FileOutputStream(dest),"UTF-8")) {
				int n;
				char[] buffer=new char[1024];
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			}
			
		}
		System.out.println("ok");
	}
```

### 压缩文件

读取压缩文件读取ZipInputStream也是一种包装类

```java
public static void main(String[] args) throws Exception {
		try(ZipInputStream zip=new ZipInputStream(new BufferedInputStream(new FileInputStream("test.jar")))){
			ZipEntry entry=null;
			while((entry=zip.getNextEntry())!=null){
				if(entry.isDirectory()){
					System.out.println("D "+entry.getName());
				}else{
					System.out.println("F "+entry.getName()+" "+entry.getSize());
					printFileContent(zip);
				}
			}
		}
	}

	private static void printFileContent(ZipInputStream zip) throws IOException {
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int n;
		while((n=zip.read(buffer))!=-1){
			output.write(buffer,0,n);
		}
		byte[] data=output.toByteArray();
		System.out.println(" size: "+data.length);
	}
```

### classpath

优点：从classpath读取文件可以避免不同环境（win和mac）文件路径不一致问题，解决**文件路径依赖**问题

- Class对象的getResourceAsStream可以从classpath读取资源
- 需要检查返回的InputStream是否为null

```java
//从classpath读取配置文件.properties
		String pros="/conf.properties";
		try(InputStream input=Main.class.getResourceAsStream(pros)){
			if(input!=null){
				System.out.println("Read /conf.properties...");
				Properties props=new Properties();
				props.load(input);
				System.out.println("name="+props.getProperty("name"));
			}else{
				System.out.println("Resource not found: "+pros);
			}
		}
		
		//从classpath读取txt文件
		String data="/com/lucas/javase/io/classpath/data.txt";
		try (InputStream input = Main.class.getResourceAsStream(data)) {
			if(input!=null){
				System.out.println("Read "+data+"...");
				BufferedReader reader=new BufferedReader(new InputStreamReader(input));
				System.out.println(reader.readLine());
			}else{
				System.out.println("Resource not found: "+pros);
			}
		}
```

### 序列化

- 把一个java对象变成二进制内容（byte[]）
- 序列化后可以把byte[]保存到文件中
- 序列化后可以把byte[]通过网络传输 

#### 标记接口

空接口叫做**标记接口**（Marker Interface）

- Serializable接口没有定义任何方法
- 一个java对象要能序列化必须实现Serializable接口

#### 反序列化

- 把一个二进制内容（byte[]）变成java对象
- 反序列化可以从文件读取byte[]变成java对象
- 反序列化可以从网络读取byte[]变成java对象

读取java对象流ObjectInputStream

input.readObject()读取java对象

可能的异常：

- ClassNotFoundException：没有找到对应Class
- InvalidClassException：Class不匹配

反序列化有JVM直接构造出java对象，不调用构造方法

```java
String dataFile="saved.data";
		/*try(ObjectOutputStream output=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))){
			//依次写入int，String，Person
			output.writeInt(999);
			output.writeUTF("Hello,world!");
			output.writeObject(new Person("lucas"));
		}*/
		System.out.println("Read ...");
		try(ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))){
			//依次读取int，String,Person
			System.out.println(input.readInt());
			System.out.println(input.readUTF());
			Person p=(Person) input.readObject();
			System.out.println(p);
		}
```

Person.java

```java
public class Person implements Serializable{
	private static final long serialVersionUID = -264706335817L;//如果修改了这里，反序列化的时候会不匹配

	public Person(String name) {
		System.out.println("Create "+name);//反序列化不执行
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}
```



#### 总结

- 可序列号java对象必须实现java.io.Serializable接口
- java序列化仅仅适用于java，如果与其他语言交互，必须使用通用序列化方法，例如JSON