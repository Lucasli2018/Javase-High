package com.lucas.javase.reflection.clazz;


public class Main {

	public static void main(String[] args) throws Exception {
		Class cls=null;
		if(true){
			cls=Class.forName("com.lucas.javase.reflection.clazz.Student");
		}else{
			//cls=Class.forName("com.lucas.javase.reflection.Teacher");
			cls=Class.forName("com.lucas.javase.reflection.Doctor");
		}
		System.out.println("class name: " + cls.getName());
		System.out.println("class simple name: " + cls.getSimpleName());
		System.out.println("package name: " + cls.getPackage().getName());
		System.out.println("is interface? " + cls.isInterface());
		// Student s = new Student();
		Hello s = (Hello) cls.newInstance();
		s.hello();
	}

}
