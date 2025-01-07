package com.jthread.taskmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jthread.taskmanagement.enums.Priority;
import com.jthread.taskmanagement.enums.Status;
import com.jthread.taskmanagement.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	
		@Query("SELECT t FROM Task t WHERE " +
			       "(:status IS NULL OR t.status = :status) " +  // Filter by status if provided
			       "AND (:priority IS NULL OR t.priority = :priority)")  // Filter by priority if provided
		public List<Task> searchTask(@Param("status") Status status, @Param("priority") Priority priority);
		
		@Query("SELECT t FROM Task t WHERE t.deadline BETWEEN :startDate AND :endDate")
		public List<Task> searchByDeadline(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

		
		

}
