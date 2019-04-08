package com.lucas.javase.generic.erasure;

import com.lucas.javase.generic.use.Student;

public class StudentPair extends Pair<Student> {

	public StudentPair(Student first, Student last) {
		super(first, last);
	}

}
