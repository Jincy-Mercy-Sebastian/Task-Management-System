package com.jthread.test_management.dto;

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
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDto {


	private Integer id;
	
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

	public UpdateTaskDto(@NotEmpty @Size(min = 6, message = "Minimum length is 6 characters") String title,
			@NotEmpty String description, @NotEmpty String status, @NotEmpty String priority,
			@Future LocalDate deadline) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.deadline = deadline;
	}
	
	
}
