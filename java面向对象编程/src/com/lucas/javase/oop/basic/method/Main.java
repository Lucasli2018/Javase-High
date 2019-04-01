package com.lucas.javase.oop.basic.method;


public class Main {

	/**
	 * - 外部代码不可以访问private字段
- 通过public方法间接设置和获取private字段
- public方法封装了数据访问
- 通过方法访问字段更安全
	 * @param args
	 */
	public static void main(String[] args) {
		Person ming = new Person();
		ming.setName("小明");
		ming.setAge(12);

		System.out.println(ming.getName());
		System.out.println(ming.getAge());
	}

}
