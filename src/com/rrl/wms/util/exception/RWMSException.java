package com.rrl.wms.util.exception;

public class RWMSException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RWMSException() {
		// TODO Auto-generated constructor stub
	}
	
	private String message = null;
	
	
	public RWMSException(String message){
		super(message);
		this.message = message;
	}
	public RWMSException(Throwable cause){
		super(cause);
	}
	
	public String toString(){
		return message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setmessage(String message) {
		this.message = message;
	}

}
