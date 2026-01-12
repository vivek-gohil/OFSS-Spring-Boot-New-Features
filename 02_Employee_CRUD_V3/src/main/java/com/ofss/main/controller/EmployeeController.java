package com.ofss.main.controller;

import java.util.List;

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

	@PostMapping("add")
	public Employee addNewEmployee(@RequestBody Employee employee) {
		log.info(employee.toString());
		return employeeService.save(employee);
	}

	@GetMapping("{id}")
	public Employee getSingleEmployee(@PathVariable Long id) {
		log.info("Retriving Employee by employeeId :: " + id);
		return employeeService.findById(id);
	}

	@GetMapping
	public List<Employee> getAllEmployees() {
		log.info("Retriving All Employees");
		return employeeService.findAll();
	}

	@PutMapping("update/{id}")
	public Employee updateExistingEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		log.info("Updating Details Employee by employeeId :: " + id);
		log.info(employee.toString());
		return employeeService.update(id, employee);
	}

	@DeleteMapping("{id}")
	public void deleteEmployee(@PathVariable Long id) {
		log.info("Deleting Employee by employeeId :: " + id);
		employeeService.delete(id);
	}

}
