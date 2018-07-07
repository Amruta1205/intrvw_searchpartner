package com.assignment.model;

public enum creditCardType {

	VISA("VISA", 4),
	MASTER("MASTER", 5),
	AMERICAN_EXPRESS("AMERICAN EXPRESS", 37),
	DISCOVER("DISCOVER", 6);
	
	
	
	private String type;
	private int startNumber;
	
	
	private creditCardType(String ttype, int sstartNumber)
	{
		type = ttype;
		startNumber = sstartNumber; 
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getStartNumber() {
		return startNumber;
	}


	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}
	
	
	
}
