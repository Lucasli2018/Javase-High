package com.lucas.javase.annotation.practise;

import java.lang.reflect.Field;


public class Main {

	public static void main(String[] args) throws Exception {
		Person p1 = new Person("Xiao Ming", 25, "100123");
		Person p2 = new Person(null, 15, "8080");
		Person p3 = new Person(null, 5, "aaaa");
		checkPerson(p1);
		checkPerson(p2);
		checkPerson(p3);
	}

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

}
