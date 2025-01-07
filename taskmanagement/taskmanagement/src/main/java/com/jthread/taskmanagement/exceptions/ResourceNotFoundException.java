package com.jthread.taskmanagement.exceptions;

public class ResourceNotFoundException extends Exception {
	public ResourceNotFoundException(String message){
		super("Resource not found " + message);
	}

}
