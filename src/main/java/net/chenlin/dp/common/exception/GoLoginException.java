package net.chenlin.dp.common.exception;

import net.chenlin.dp.common.constant.RestApiConstant;

/**
 * 自定义异常
 * @author wang<fangyuan.co@outlook.com>
 */
public class GoLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    private String msg;

    private int code = 1001;

    public GoLoginException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public GoLoginException(RestApiConstant.TokenErrorEnum tokenErrorEnum) {
		super(tokenErrorEnum.getMsg());
		this.msg = tokenErrorEnum.getMsg();
		this.code = tokenErrorEnum.getCode();
	}

	public GoLoginException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public GoLoginException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public GoLoginException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
