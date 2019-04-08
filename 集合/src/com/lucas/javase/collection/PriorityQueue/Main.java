package com.lucas.javase.collection.PriorityQueue;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Queue<Person> queue = new PriorityQueue<>(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		queue.offer(new Person("Ming", 12));
		queue.offer(new Person("Hong", 15));
		queue.offer(new Person("Jun", 17));
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}

}
