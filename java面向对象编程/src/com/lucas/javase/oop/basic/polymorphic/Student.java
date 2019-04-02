package com.lucas.javase.oop.basic.polymorphic;


public class Student extends Person {

	private int score;

	public Student(String name) {
		super(name);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return super.hello()+"!";
	}

	@Override
	public void run() {
		System.out.println(this.getName() + " is running very fast !");
	}
	
	

}
