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

### ReentrantLock

ReentrantLock可以替代synchronized

ReentrantLock获取锁更安全

必须使用`try … finally`保证正确获取和释放锁

`tryLock()`可指定超时

ReentrantLock是可重入锁：一个线程可以多次获取同一个锁

```java
class Counter {

	private Lock lock = new ReentrantLock();

	private int value = 0;

	public void add(int m) {
		lock.lock();
		try {
			this.value += m;
		} finally {
			lock.unlock();
		}
	}

	public void dec(int m) {
		lock.lock();
		try {
			this.value -= m;
		} finally {
			lock.unlock();
		}
	}

	public int get() {
		lock.lock();
		try {
			return this.value;
		} finally {
			lock.unlock();
		}
	}
}

public class Main {

	final static int LOOP = 100;

	public static void main(String[] args) throws Exception {
		Counter counter = new Counter();
		Thread t1 = new Thread() {
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					counter.add(1);
				}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					counter.dec(1);
				}
			}
		};
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(counter.get());
	}

```

### ReadWriteLock

reentrant可重入的

使用ReadWriteLock可以提高读取效率：

* ReadWriteLock只允许一个线程写入

* ReadWriteLock允许多个线程同时读取

* ReadWriteLock适合读多写少的场景

![1555215753479](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1555215753479.png)

### Condition

原理就是给线程的组合对象加锁

Condition可以替代wait / notify

Condition对象必须从ReentrantLock对象获取

ReentrantLock＋Condition可以替代synchronized + wait / notify

```java
class TaskQueue {

	final Queue<String> queue = new LinkedList<>();

	final Lock lock = new ReentrantLock();
	final Condition notEmpty = lock.newCondition();

	public String getTask() throws InterruptedException {
		lock.lock();
		try {
			while (this.queue.isEmpty()) {
				notEmpty.await();
			}
			return queue.remove();
		} finally {
			lock.unlock();
		}
	}

	public void addTask(String name) {
		lock.lock();
		try {
			this.queue.add(name);
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
```



### concurrent集合

使用java.util.concurrent提供的Blocking集合可以简化多线程编程：

* CopyOnWriteArrayList
* ConcurrentHashMap
* CopyOnWriteArraySet
* **ArrayBlockingQueue**
* LinkedBlockingQueue
* LinkedBlockingDeque

多线程同时访问Blocking集合是安全的

尽量使用JDK提供的concurrent集合，避免自己编写同步代码



### Atomic原子操作

使用java.util.atomic提供的原子操作可以简化多线程编程：

AtomicInteger／AtomicLong／AtomicIntegerArray等

原子操作实现了无锁的线程安全

适用于计数器，累加器等

### ExecutorService线程池

线程池：

- 维护多个线程，处于等待状态
- 如果有新任务，就分配一个空闲线程执行
- 如果所有线程都处于忙碌状态，新任务放入队列等待

JDK提供了ExecutorService实现了线程池功能

线程池内部维护一组线程，可以高效执行大量小任务

Executors提供了静态方法创建不同类型的ExecutorService

常用ExecutorService：

* FixedThreadPool：线程数固定
* CachedThreadPool：线程数根据任务动态调整
* SingleThreadExecutor：仅单线程执行

必须调用shutdown()关闭ExecutorService

**ScheduledThreadPool可以定期调度多个任务（可取代Timer）**

#### 队列线程池

```java
public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(new PrintTask("Bob"));
		executor.submit(new PrintTask("Alice"));
		executor.submit(new PrintTask("Tim"));
		executor.submit(new PrintTask("Robot"));//在队列等待
		Thread.sleep(10000);
		executor.shutdown();
	}
```

#### 单线程池

```java
public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newSingleThreadExecutor();//单线程串行方式执行
		executor.submit(new PrintTask("Bob"));
		executor.submit(new PrintTask("Alice"));
		executor.submit(new PrintTask("Tim"));
		executor.submit(new PrintTask("Robot"));
		Thread.sleep(10000);
		executor.shutdown();
	}
```

#### 自动调整线程池

```java
public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();//自动创建4个线程
		executor.submit(new PrintTask("Bob"));
		executor.submit(new PrintTask("Alice"));
		executor.submit(new PrintTask("Tim"));
		executor.submit(new PrintTask("Robot"));
		Thread.sleep(10000);
		executor.shutdown();
	}
```

#### 限制数量线程池

```java
public static void main(String[] args) throws Exception {
		ExecutorService executor =new ThreadPoolExecutor(0, 10,//最多允许10个线程
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
		executor.submit(new PrintTask("Bob"));
		executor.submit(new PrintTask("Alice"));
		executor.submit(new PrintTask("Tim"));
		executor.submit(new PrintTask("Robot"));
		Thread.sleep(10000);
		executor.shutdown();
	}
```

Timer可以定时执行一个任务，但是一个Timer对应一个Thread，执行定期执行一个任务，如果要执行多个任务，需要启动多个Timer

而一个ThreadPool可以调度多个任务，所以可以使用ScheduleThreadPool取代旧的Timer类

### Future

Future表示一个未来可能会返回的结果

提交Callable任务，可以获得一个Future对象

可以用Future在将来某个时刻获取结果

#### 异步下载

get方法可能会阻塞

```java
class DownloadTask implements Callable<String> {
	String url;

	public DownloadTask(String url) {
		this.url = url;
	}

	public String call() throws Exception {
		System.out.println("Start download " + url + "...");
		URLConnection conn = new URL(this.url).openConnection();
		conn.connect();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
			String s = null;
			StringBuilder sb = new StringBuilder();
			while ((s = reader.readLine()) != null) {
				sb.append(s).append("\n");
			}
			return sb.toString();
		}
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		DownloadTask task = new DownloadTask("https://www.sina.com.cn/");
		Future<String> future = executor.submit(task);
		String html = future.get();
		System.out.println(html);
		executor.shutdown();
	}
}
```

### CompletableFuture

CompletableFuture的优点：

* 异步任务结束时，会自动回调某个对象的方法
* 异步任务出错时，会自动回调某个对象的方法
* 主线程设置好回调后，不再关心异步任务的执行

CompletableFuture对象可以指定异步处理流程：

* thenAccept()处理正常结果
* exceptional()处理异常结果
* thenApplyAsync() 用于串行化另一个CompletableFuture
* anyOf / allOf 用于并行化两个CompletableFuture

#### 并行下载

```java
public static void main(String[] args) throws Exception {
		String name = "上证指数";
		CompletableFuture<String> getStockCodeFuture = CompletableFuture.supplyAsync(new StockLookupSupplier(name));
		CompletableFuture<Price> getStockPriceFuture = getStockCodeFuture.thenApplyAsync(new Function<String, Price>() {
			public Price apply(String code) {
				System.out.println("got code: " + code);
				try {
					String url = "http://hq.sinajs.cn/list=" + code;
					String result = DownloadUtil.download(url);
					String[] ss = result.split(",");
					return new Price(code, Float.parseFloat(ss[3]));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
		getStockPriceFuture.thenAccept(new Consumer<Price>() {
			public void accept(Price p) {
				System.out.println(p.code + ": " + p.price);
			}
		});
		getStockPriceFuture.join();
	}
```



```
public static void main(String[] args) throws Exception {
		CompletableFuture<StockPrice> getStockFromNetease = CompletableFuture.supplyAsync(new StockFromNetease());
		CompletableFuture<StockPrice> getStockFromSina = CompletableFuture.supplyAsync(new StockFromSina());
		CompletableFuture<Object> getStock = CompletableFuture.anyOf(getStockFromSina, getStockFromNetease);
		getStock.thenAccept(new Consumer<Object>() {
			public void accept(Object result) {
				System.out.println("Reuslt: " + result);
			}
		});
		getStock.join();
	}
```

### Fork/Join

Fork/Join是一种基于“分治”的算法：分解任务＋合并结果

ForkJoinPool线程池可以把一个大任务分拆成小任务并行执行

任务类必须继承自RecursiveTask／RecursiveAction

使用Fork/Join模式可以进行并行计算提高效率

java.util.Arrays.parallelSort（array)内部就是使用forkjoin对数据进行排序

### ThreadLocal

- Treah是多线程基础

- ExecutorService线程池

- ScheduledThreadPool定时任务

- Fork/Join并行

完成多任务模式

每一个用户访问浏览器，就从线程池取出一个线程对象，或者创建一个新的线程对象

调用Thread.currentThread()获取当前线程。

JDK提供了ThreadLocal，在一个线程中传递同一个对象。

ThreadLocal表示线程的“局部变量”，它确保每个线程的ThreadLocal变量都是各自独立的。

ThreadLocal适合在一个线程的处理流程中保持上下文（避免了同一参数在所有方法中传递）

使用ThreadLocal要用try … finally结构。（finally中一定要清楚状态）

#### 通过线程对象传递参数

```java
class ProcessThread extends Thread {
	User user;

	ProcessThread(User user) {
		this.user = user;
	}

	public void run() {
		try (UserContext ctx = new UserContext(user)) {
			// step 1:
			new Greeting().hello();//尽管hello方法没有参数，但是通过线程对象已经将需要的参数传递进去了
			// step 2:
			Level.checkLevel();
			// step 3:
			// TODO:
		}
	}
}
```

