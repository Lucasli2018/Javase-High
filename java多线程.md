### Java多线程

系统轮流让多个任务交替执行，一个任务就是一个进程process（比如word），进程内部可以有多个子任务（线程thread，比如一边打字，一边拼写检查），**线程是操作系统调度最小任务单位**

实现多任务：

- 多进程（每个进程只有一个thread）
- 多线程（一个进程有多个线程thread）
- 多进程+多线程（复杂度最高）

java多线程模型是java最基本并发模型

是网络编程，数据库编程，web变成的模型

**必须掌握多线程才能深入学习**

一个java程序实际上就是一个**jvm进程**process

jvm用一个主**线程**来执行main方法

在main方法中有可以启动多个**线程**

多线程编程的特点：

* 多线程需要读写共享数据
* 多线程经常需要同步
* 多线程编程的复杂度高，调试更困难

### 创建新线程

1. 从Thread派生
2. 实现Runnable接口

必须调用`start()`方法才能启动新线程

`Thread.sleep()`可以把当前线程暂停一段时间

### 线程的状态

一个线程对象只能调用一次start()

线程状态：

-  New 

-  Runnable / Blocked / Waiting / Timed Waiting 
- Terminated

线程终止：

- run方法执行到return
- 未捕获异常
- stop方法

通过对另一个线程对象调用join()方法可以等待其执行结束

```java
public static void main(String[] args) throws Exception {
		Thread t1 = new HelloThread("Bob");
		System.out.println("START");
		t1.start();
		t1.join(); // 等待线程t1执行结束
		System.out.println("END");
	}
```

可以指定等待时间，超过等待时间线程仍然没有结束就不再等待

对已经运行结束的线程调用join()方法会立刻返回

### 中断线程

- interrupt方法
- volatile修饰符

调用interrupt()方法可以中断一个线程

通过检测isInterrupted()标志获取当前线程是否已中断

如果线程处于等待状态，该线程会捕获InterruptedException

isInterrupted()为true或者捕获了InterruptedException都应该立刻结束

```java
class HelloThread extends Thread {
	@Override
	public void run() {
		while (!isInterrupted()) {
			System.out.println("Hello!");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Interrupted!");
				break;//这句话很关键的
			}
		}
		System.out.println("Thread end");
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		HelloThread t = new HelloThread();
		t.start();
		Thread.sleep(1000);
		t.interrupt();
		System.out.println("Main end");
	}
}
```

通过标志位判断需要正确使用volatile关键字

volatile关键字解决了共享变量在线程间的可见性问题

volatile关键字目的是告诉虚拟机：

- 每次访问变量时，总是获取主内存最新值
- 每次修改变量后，立刻回写主内存

```java
class HelloThread extends Thread {
	public volatile boolean running=true;
	@Override
	public void run() {
		while (running) {
			System.out.println("Hello!");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Interrupted!");
				break;//这句话很关键的
			}
		}
		System.out.println("Thread end");
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		HelloThread t = new HelloThread();
		t.start();
		Thread.sleep(1000);
		t.running=false;
		System.out.println("Main end");
	}
}
```

### 守护线程

如果某个线程不结束（比如死循环，main结束了，但是线程仍然在循环，jvm无法退出），jvm进程就无法结束，有谁负责结束这类线程？

- 守护线程是为其他线程服务的线程（main执行结束守护线程自动结束，结束jvm）

- 所有非守护线程都执行完毕后，jvm退出

![1555166943341](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555166943341.png)

**守护线程不能持有资源（如打开文件等）**

创建守护线程：

```java
public static void main(String[] args) throws Exception {
		System.out.println("Main start");
		TimerThread t = new TimerThread();
		t.setDaemon(true);
		t.start();
		Thread.sleep(5000);
		System.out.println("Main end");
	}
```

### 线程同步

多线程同时修改变量，会造成逻辑错误

需要通过**synchronized**同步对**一个对象**进行锁，如果是不同对象，锁是无效的。加锁后效率会下降

**注意加锁对象必须是同一个实例**

```java
class AddThread extends Thread {
	public void run() {
		for (int i = 0; i < Main.LOOP; i++) {
			synchronized(Main.LOCK){
				Main.count += 1;
			}
		}
	}
}

class DecThread extends Thread {
	public void run() {
		for (int i = 0; i < Main.LOOP; i++) {
			synchronized(Main.LOCK){
				Main.count -= 1;
			}
		}
	}
}

public class Main {

	final static int LOOP = 10000;
	public final static Object LOCK=new Object();

	public static int count = 0;

	public static void main(String[] args) throws Exception {
		Thread t1 = new AddThread();
		Thread t2 = new DecThread();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(count);
	}
}
```

对JVM定义的**单个原子操作**不需要同步

- 基本类型赋值
- 引用类型赋值

### synchronized方法

用synchronized修饰方法可以把整个方法变为同步代码块

synchronized方法加锁对象是this

通过合理的设计和数据封装可以让一个类变为“线程安全”

- java.lang.StringBuffer就是线程安全的

因为他的方法都是用synchronized修饰的

![1555168352695](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555168352695.png)

一个类没有特殊说明，默认不是thread-safe

线程安全的类：

- 不变类:String,Integer,LocalDate
- 没有成员变量：Math
- 正确使用synchronized：StringBuffer

非线程安全的类：

- ArrayList



多线程能否访问某个非线程安全的实例，需要具体问题具体分析

### 死锁

死锁产生的条件：

- 多线程各自持有不同对象的锁
- 并互相试图获取对方已持有的锁
- 双方无限等待下去：导致死锁

死锁后只能强制停止jvm

如何避免死锁：

多线程获取锁的顺序要一致

```java
public void a2b(int balance) {
		synchronized (lockA) {
			accountA -= balance;
			synchronized (lockB) {
				accountB += balance;
			}
		}
	}

	public void b2a(int balance) {
		synchronized (lockA) {
			accountA += balance;
			synchronized (lockB) {
				accountB -= balance;

			}
		}
	}
```

### wait / notify

wait方法是native（C语言）实现的，只能在synchronized代码中调用wait，这个方法会释放锁

wait / notify用于多线程协调运行：

- 在synchronized内部可以调用wait()使线程进入等待状态

- 必须在已获得的锁对象上调用wait()方法

- 在synchronized内部可以调用notify / notifyAll()唤醒其他等待线程

- 必须在已获得的锁对象上调用notify / notifyAll()方法

```java
class TaskQueue {

	final Queue<String> queue = new LinkedList<>();

	public synchronized String getTask() throws InterruptedException {
		while (this.queue.isEmpty()) {
			this.wait();
		}
		return queue.remove();
	}

	public synchronized void addTask(String name) {
		this.queue.add(name);
		this.notifyAll();
	}
}

class WorkerThread extends Thread {
	TaskQueue taskQueue;

	public WorkerThread(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void run() {
		while (!isInterrupted()) {
			String name;
			try {
				name = taskQueue.getTask();
			} catch (InterruptedException e) {
				break;
			}
			String result = "Hello, " + name + "!";
			System.out.println(result);
		}
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		TaskQueue taskQueue = new TaskQueue();
		WorkerThread worker = new WorkerThread(taskQueue);
		worker.start();
		// add task:
		taskQueue.addTask("Bob");
		Thread.sleep(1000);
		taskQueue.addTask("Alice");
		Thread.sleep(1000);
		taskQueue.addTask("Tim");
		Thread.sleep(1000);
		worker.interrupt();//线程终端退出while死循环
		worker.join();
		System.out.println("END");
	}
}
```

改一改就可以实现顺序下载多个图片