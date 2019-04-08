package com.feiyangedu.sample;

import java.util.Deque;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		Deque<String> deque = new LinkedList<>();
		deque.offerLast("end"); // "end"
		deque.offerFirst("C"); // "C", "end"
		deque.offerFirst("B"); // "B", "C", "end"
		deque.offerFirst("A"); // "A", "B", "C", "end"
		System.out.println(deque.pollLast());
		System.out.println(deque.pollFirst());
		System.out.println(deque.pollFirst());
		System.out.println(deque.pollFirst());
		System.out.println(deque.pollFirst());
	}

}
