package com.jthread.taskmanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.jthread.taskmanagement.dto.CreateTaskDto;
import com.jthread.taskmanagement.dto.UpdateTaskDto;
import com.jthread.taskmanagement.exceptions.ResourceNotFoundException;
import com.jthread.taskmanagement.model.Task;

public interface TaskService {
	public Task addTask(CreateTaskDto createTaskDto);
	public List<Task> getTasks();
	public Task updateTask(Integer id,UpdateTaskDto updateTaskDto) throws ResourceNotFoundException;
	public Task getTaskById( Integer id)  throws ResourceNotFoundException;
	public void deleteTask(Integer id);
	public List<Task> searchTask(String status, String priority);
	public List<Task> searchByDeadline(LocalDate startDate, LocalDate endDate);
}

