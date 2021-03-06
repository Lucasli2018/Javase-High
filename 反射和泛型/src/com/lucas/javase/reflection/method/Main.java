package com.lucas.javase.reflection.method;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		Student s = new Student();
		Class cls = s.getClass();
		Method m = cls.getMethod("setAddress", String.class);
		m.invoke(s, "shanghai");
		//printMethodInfo(m);
		s.hello();
		BeanInfo bi=Introspector.getBeanInfo(cls);
		for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {
			System.out.println("=========");
			System.out.println(pd.getName());
			if("class".equals(pd.getName())){
				System.out.println("跳过...");
				continue;
			}
			printMethodInfo(pd.getReadMethod());
			printMethodInfo(pd.getWriteMethod());
		}
	}

	static void printMethodInfo(Method m) {
		System.out.println(m);
		System.out.println("method name: " + m.getName());
		System.out.println("return type: " + m.getReturnType());
		System.out.println("parameters: " + Arrays.toString(m.getParameterTypes()));
		System.out.println("method modifier: " + m.getModifiers());
	}

}
