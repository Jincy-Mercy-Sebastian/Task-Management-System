package com.jthread.taskmanagement.model;

import java.time.LocalDate;

import com.jthread.taskmanagement.enums.Priority;
import com.jthread.taskmanagement.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name ="title")
	private String title;
	
	@Column(name ="description")
	private String description;
	
	@Column(name ="status")
	private Status status;
	
	@Column(name ="priority")
	private Priority priority;
	
	@Column(name ="deadline")
	private LocalDate deadline;

	public Task(String title, String description, Status status, Priority priority, LocalDate deadline) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.deadline = deadline;
	}
	
	
	
}
