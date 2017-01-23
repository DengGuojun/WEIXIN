package com.lpmas.weixin.api.base;

public class ApiException extends Exception {
	private static final long serialVersionUID = 2582433005104559678L;

	private int code;

	public ApiException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public ApiException(int code, Exception e) {
		super(e);
		this.code = code;
	}

	public int getErrorCode() {
		return code;
	}
}
