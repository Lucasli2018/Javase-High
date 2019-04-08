package com.lucas.javase.generic.erasure;


public class Pair<T> {

	private T first;
	private T last;

	public Pair(T first, T last) {
		this.first = first;
		this.last = last;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getLast() {
		return last;
	}

	public void setLast(T last) {
		this.last = last;
	}

	public String toString() {
		return "Pair(" + first + ", " + last + ")";
	}

//	public boolean equals(T pair) {//由于jvm内部将T作为Object处理，所以这里方法重名
//		return true;
//	}
}
