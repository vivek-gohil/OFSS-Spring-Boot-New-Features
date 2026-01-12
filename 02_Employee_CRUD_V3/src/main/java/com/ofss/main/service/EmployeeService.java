package com.ofss.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ofss.main.entity.Employee;
import com.ofss.main.exception.ResourceNotFoundException;
import com.ofss.main.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public Employee save(Employee employee) {
		log.info("Saving Employee :: " + employee.getName());
		return employeeRepository.save(employee);
	}

	public List<Employee> findAll() {
		log.info("Retriving All Employees");
		return employeeRepository.findAll();
	}

	public Employee findById(Long id) {
		log.info("Retriving Employee :: employeeId :: " + id);
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found, Invalid Employee Id"));
	}

	public Employee update(Long id, Employee employee) {
		log.info("Updating Employee with employeeId :: " + id);
		Employee existingEmployee = findById(id);
		existingEmployee.setName(employee.getName());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setSalary(employee.getSalary());

		return employeeRepository.save(existingEmployee);
	}

	public void delete(Long id) {
		log.info("Deleting Employee with employeeId :: " + id);
		employeeRepository.delete(findById(id));
	}
}
