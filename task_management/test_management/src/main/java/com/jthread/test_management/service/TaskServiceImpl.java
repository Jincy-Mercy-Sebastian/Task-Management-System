package com.jthread.test_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jthread.test_management.dto.CreateTaskDto;
import com.jthread.test_management.dto.UpdateTaskDto;
import com.jthread.test_management.enums.Priority;
import com.jthread.test_management.enums.Status;
import com.jthread.test_management.exceptions.ResourceNotFoundException;
import com.jthread.test_management.model.Task;
import com.jthread.test_management.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task addTask(CreateTaskDto createTaskDto) {
		Task task=new Task();
		task.setTitle(createTaskDto.getTitle());
		task.setDescription(createTaskDto.getDescription());
		task.setDeadline(createTaskDto.getDeadline());
		try {
			Priority priority = Priority.valueOf(createTaskDto.getPriority().toUpperCase()); // Convert to enum, case-insensitive
			task.setPriority(priority);
			
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority value: " + createTaskDto.getPriority());
        }
		
		try {			
			Status status=Status.valueOf(createTaskDto.getStatus().toUpperCase());
			task.setStatus(status);
			System.out.println(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority value: " + createTaskDto.getStatus());
        }
		
		Task createdTask=taskRepository.save(task);
		return createdTask;
	}

	
	@Override
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}


	@Override
	public Task updateTask(Integer id, UpdateTaskDto updateTaskDto) throws ResourceNotFoundException {
		Optional<Task> taskContainer=taskRepository.findById(id);
		if ( taskContainer.isPresent()) {
			Task task=taskContainer.get();
			task.setTitle(updateTaskDto.getTitle());
			task.setDescription(updateTaskDto.getDescription());
			task.setDeadline(updateTaskDto.getDeadline());
			try {
				Priority priority = Priority.valueOf(updateTaskDto.getPriority().toUpperCase()); // Convert to enum, case-insensitive
				task.setPriority(priority);
				
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("Invalid priority value: " + updateTaskDto.getPriority());
	        }
			
			try {			
				Status status=Status.valueOf(updateTaskDto.getStatus().toUpperCase());
				task.setStatus(status);
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("Invalid priority value: " + updateTaskDto.getStatus());
	        }
			
			Task updatedTask=taskRepository.save(task);
			return updatedTask;
		}else {
			throw new ResourceNotFoundException("Task Id not found");
		}
	}


	@Override
	public Task getTaskById(Integer id) throws ResourceNotFoundException {
		Optional<Task> taskContainer=taskRepository.findById(id);
		if ( taskContainer.isPresent()) {
			Task task=taskContainer.get();
			return task;
		}
		else {
			throw new ResourceNotFoundException("Task Id not found");
		}
	}


	@Override
	public void  deleteTask(Integer id) {
		Optional<Task> taskContainer=taskRepository.findById(id);
		if ( taskContainer.isPresent()) {
			Task task=taskContainer.get();
			taskRepository.delete(task);
		}
	
	}


	@Override
	public List<Task> searchTask(String status, String priority) {
	    Priority priorityToSearch = null;
	    Status statusToSearch = null;

	    if (priority != null && !priority.isEmpty()) {
	        try {
	            priorityToSearch = Priority.valueOf(priority.toUpperCase()); 
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("Invalid priority");
	        }
	    }

	    if (status != null && !status.isEmpty()) {
	        try {
	            statusToSearch = Status.valueOf(status.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("Invalid status");
	        }
	    }

	    return taskRepository.searchTask(statusToSearch, priorityToSearch);
	}


	@Override
	public List<Task> searchByDeadline(LocalDate startDate, LocalDate endDate) {
		return taskRepository.searchByDeadline(startDate, endDate);
	}



}
