package com.sumit.assignmentfive.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private int code;

 
    public ErrorDetails(Date timestamp, String message, String details, int code) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Date getTimestamp() {
         return timestamp;
    }

    public String getMessage() {
         return message;
    }

    public String getDetails() {
         return details;
    }
}