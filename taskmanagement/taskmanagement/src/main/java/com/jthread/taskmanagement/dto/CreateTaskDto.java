package com.jthread.taskmanagement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {

	@NotEmpty
	@Size(min = 6, message = "Minimum length is 6 characters")
	private String title;
	@NotEmpty
	private String description;
	@NotEmpty
	private String status;
	@NotEmpty
	private String priority;

	@Future
	private LocalDate deadline;
	

	
	
}

