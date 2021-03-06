package com.lucas.javase.exception.Throw;


public class Main {

	
	public static void main(String[] args) {
		process1();
	}

	static void process1() {
		try {
			process2();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("====================");
		} finally {
			System.out.println("END");
		}
	}

	static void process2() {
		process3();
	}

	static void process3() {
		Exception original=null;
		
		System.out.println(Integer.parseInt("12"));
		try{
			Integer.parseInt(null);
		}catch(NumberFormatException e){
			original=e;
			throw new IllegalArgumentException(e);//先执行finally语句，如果finally语句异常将不会执行这里
		}finally {
			System.out.println("finally....");
			try{
				throw new NullPointerException();//在finally中尽量不要抛出异常，否则会导致suppressed exception，异常被屏蔽
			}catch(Exception e){
				if(original!=null){
					original.addSuppressed(e);
				}else{
					original=e;
				}
			}
			
			if(original!=null){
				try {
					throw original;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
