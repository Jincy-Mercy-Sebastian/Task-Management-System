package com.jthread.test_management.service;

import java.time.LocalDate;
import java.util.List;

import com.jthread.test_management.dto.CreateTaskDto;
import com.jthread.test_management.dto.UpdateTaskDto;
import com.jthread.test_management.exceptions.ResourceNotFoundException;
import com.jthread.test_management.model.Task;

public interface TaskService {
	public Task addTask(CreateTaskDto createTaskDto);
	public List<Task> getTasks();
	public Task updateTask(Integer id,UpdateTaskDto updateTaskDto) throws ResourceNotFoundException;
	public Task getTaskById( Integer id)  throws ResourceNotFoundException;
	public void deleteTask(Integer id);
	public List<Task> searchTask(String status, String priority);
	public List<Task> searchByDeadline(LocalDate startDate, LocalDate endDate);
}
