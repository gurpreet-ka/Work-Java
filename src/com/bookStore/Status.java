package com.bookStore;

public enum Status {

	OK ("OK",0),

	NOT_IN_STOCK("NOT_IN_STOCK",1),

	Does_not_exist("DOES_NOT_EXIST",2);
	
	private String status;
	private int value;
	
	private Status(String status, int value) {
		this.status=status;
		this.value=value;
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
