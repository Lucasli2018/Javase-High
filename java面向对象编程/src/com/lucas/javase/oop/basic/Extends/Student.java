package com.lucas.javase.oop.basic.Extends;


public class Student extends Person {

	
	public Student(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private int score;

	public int getScore() {
		return score;
	}
	public void run(){
		System.out.println(this.toString()+" runing...");
	}

	public void setScore(int score) {
		this.score = score;
	}

}
