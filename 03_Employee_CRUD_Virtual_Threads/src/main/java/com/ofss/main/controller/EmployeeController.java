package com.ofss.main.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.main.entity.Employee;
import com.ofss.main.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("employeecrudapi/employees")
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;

	@Autowired
	private Executor virtualThreadExecutor;

	@PostMapping("add")
	public Employee addNewEmployee(@RequestBody Employee employee) {
		log.info(employee.toString());
		return employeeService.save(employee);
	}

	// Apache Benchmark - send concurrent request to api and check
	// 1. on Tomcat Thread Pool - Platform Thread
	// 2. on Virtual Thread
	@GetMapping("{id}")
	public String getSingleEmployee(@PathVariable Long id) {
		log.info("Retriving Employee by employeeId :: " + id);

		try {
			employeeService.findById(id);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return "Check your file";
	}

	@GetMapping("virtual/{id}")
	public String getSingleEmployeeVirtual(@PathVariable Long id) {
		log.info("Retriving Employee by employeeId :: " + id);
		virtualThreadExecutor.execute(() -> {
			try {
				employeeService.findById(id);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		});

		return "Check your file";
	}

	@GetMapping
	public List<Employee> getAllEmployees() {
		log.info("Retriving All Employees");
		return employeeService.findAll();
	}

}
