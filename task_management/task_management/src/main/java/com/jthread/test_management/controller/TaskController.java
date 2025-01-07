package com.jthread.test_management.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jthread.test_management.dto.CreateTaskDto;
import com.jthread.test_management.dto.UpdateTaskDto;
import com.jthread.test_management.exceptions.ResourceNotFoundException;
import com.jthread.test_management.model.Task;
import com.jthread.test_management.service.TaskService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "http://localhost:3000/")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/createTask")
	public ResponseEntity<Object> createTask(@Valid @RequestBody CreateTaskDto createTaskDto , BindingResult result) {
		 if(result.hasErrors()) {
	            var errorList = result.getAllErrors();
	            var errorMap = new HashMap<String,String>();
	            for(int i=0; i<errorList.size();i++) {
	                var error = (FieldError)errorList.get(i);
	                errorMap.put(error.getField(), error.getDefaultMessage());
	            }
	            return ResponseEntity.badRequest().body(errorMap);
		 }
		 
		 Task createdTask= taskService.addTask(createTaskDto);
		 var response = new HashMap<String, Object>();
	     response.put("Task", createdTask);
	     return ResponseEntity.ok(response);
	}
	
	@GetMapping("/viewTasks")
	public List<Task> getTasks(){
		return taskService.getTasks();
	}
	
	@PutMapping("/updateTask/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable Integer id, @Valid @RequestBody UpdateTaskDto updateTaskDto, BindingResult result) throws ResourceNotFoundException {
		if(result.hasErrors()) {
            var errorList = result.getAllErrors();
            var errorMap = new HashMap<String,String>();
            for(int i=0; i<errorList.size();i++) {
                var error = (FieldError)errorList.get(i);
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMap);
	 }
	 
	 Task createdTask= taskService.updateTask(id, updateTaskDto);
	 var response = new HashMap<String, Object>();
     response.put("Task", createdTask);
     return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/get/{id}")
	public Task getTaskById(@PathVariable Integer id) throws ResourceNotFoundException {
		return taskService.getTaskById(id);
	}
	
	@DeleteMapping("/deleteTask/{id}")
	public void deleteTask(@PathVariable Integer id) {
		taskService.deleteTask(id);
	}
	
	@GetMapping("/search")
	public List<Task> getTaskById(@RequestParam String status, @RequestParam String priority)  {
		return taskService.searchTask(status, priority);
	}
	
	@GetMapping("/searchTaskbyDeadLine")
	public List<Task> searchByDeadline(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
		return taskService.searchByDeadline(startDate, endDate);
	}

}
