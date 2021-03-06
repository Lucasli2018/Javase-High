package com.lucas.javase.collection.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Queue<Person> queue = new LinkedList<>();
		queue.offer(new Person("Ming", 12));
		queue.offer(new Person("Hong", 15));
		queue.offer(new Person("Jun", 17));
		queue.offer(null);
		queue.offer(null);
		queue.offer(new Person("lucas", 17));
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());//null
		if (!queue.isEmpty()) {
			System.out.println(queue.remove());// exception
		}
	}

}
