package com.ofss.main.exception;

import java.net.URI;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
			HttpServletRequest httpServletRequest) {

		log.info("ResourceNotFoundException");
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

		problemDetail.setTitle("EmployeeId Not Found");
		problemDetail.setDetail(resourceNotFoundException.getMessage());
		problemDetail.setType(URI.create("http://localhost:8080/employeecrudapi/employees"));
		problemDetail.setInstance(URI.create(httpServletRequest.getRequestURI()));

		problemDetail.setProperty("timestamp", Instant.now());
		problemDetail.setProperty("ErrorCode", "EMPLOYEE_NOT_FOUND");

		return problemDetail;

	}

}
