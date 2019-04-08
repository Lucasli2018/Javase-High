package com.feiyangedu.sample;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Calculator {

	public Object[] compile(String s) {
		Object[] parsed = parseAsExpression(s);
		List<Object> output = new LinkedList<>();
		Deque<Character> stack = new LinkedList<>();
		for (Object e : parsed) {
			if (e instanceof Integer) {
				output.add(e);
			} else {
				char ch = (Character) e;
				switch (ch) {
				case ')':
					// find '(' in stack:
					for (;;) {
						if (stack.isEmpty()) {
							throw new IllegalStateException("Compile error: " + s);
						}
						char top = stack.pop();
						if (top == '(') {
							break;
						} else {
							output.add(top);
						}
					}
					break;
				case '(':
					stack.push(ch);
					break;
				case '+':
				case '-':
				case '*':
				case '/':
					// find all operators that >= ch:
					while (!stack.isEmpty()) {
						char first = stack.peek();
						if (priority(first) >= priority(ch)) {
							stack.pop();
							output.add(first);
						} else {
							break;
						}
					}
					stack.push(ch);
					break;
				default:
					throw new IllegalStateException("Compile error: " + s);
				}
			}
		}
		while (!stack.isEmpty()) {
			output.add(stack.pop());
		}
		return output.toArray();
	}

	public int calculate(Object[] expression) {
		Deque<Integer> stack = new LinkedList<>();
		for (Object e : expression) {
			if (e instanceof Integer) {
				stack.push((Integer) e);
			} else {
				char op = (Character) e;
				int n1 = stack.pop();
				int n2 = stack.pop();
				int r = operate(op, n2, n1);
				stack.push(r);
			}
		}
		return stack.pop();
	}

	Object[] parseAsExpression(String s) {
		List<Object> list = new ArrayList<>();
		for (char ch : s.toCharArray()) {
			if (ch >= '0' && ch <= '9') {
				int n = Integer.parseInt(String.valueOf(ch));
				list.add(n);
			} else if ("+-*/()".indexOf(ch) != (-1)) {
				list.add(ch);
			} else if (ch == ' ') {
				// ignore white space
			} else {
				throw new IllegalArgumentException("Compile error: invalid char \'" + ch + "\'");
			}
		}
		return list.toArray();
	}

	// priority from high to low: '*', '/' > '+', '-' > '('
	int priority(char op) {
		switch (op) {
		case '*':
		case '/':
			return 2;
		case '+':
		case '-':
			return 1;
		case '(':
			return 0;
		default:
			throw new IllegalArgumentException("bad operator: " + op);
		}
	}

	int operate(char operator, int a, int b) {
		switch (operator) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		default:
			throw new UnsupportedOperationException();
		}
	}

}
