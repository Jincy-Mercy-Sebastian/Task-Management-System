package com.jthread.test_management.exceptions;

public class ResourceNotFoundException extends Exception {
	public ResourceNotFoundException(String message){
		super("Resource not found " + message);
	}

}
