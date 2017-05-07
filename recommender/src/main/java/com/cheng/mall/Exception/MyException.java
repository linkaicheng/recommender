package com.cheng.mall.Exception;

public class MyException extends RuntimeException {
	static final long serialVersionUID = -70348975766939L;

	public MyException() {

	}

	public MyException(String msg) {
		super(msg);
	}

}
