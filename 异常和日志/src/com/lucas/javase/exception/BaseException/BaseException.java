package com.lucas.javase.exception.BaseException;


public class BaseException extends RuntimeException {

	//鼠标右键---source--generate constructors from superclass--选择所有的public的方法（绿色的）
	public BaseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
