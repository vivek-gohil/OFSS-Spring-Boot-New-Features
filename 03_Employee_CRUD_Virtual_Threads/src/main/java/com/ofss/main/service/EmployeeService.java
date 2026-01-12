package com.ofss.main.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${app.file.output-dir}")
	private String outputDir;

	public Employee save(Employee employee) {
		log.info("Saving Employee :: " + employee.getName());
		return employeeRepository.save(employee);
	}

	public List<Employee> findAll() {
		log.info("Retriving All Employees");
		return employeeRepository.findAll();
	}

	public void findById(Long id) throws IOException {
		log.info("Retriving Employee :: employeeId :: " + id);
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found, Invalid Employee Id"));

		writeEmployeeToFile(employee);
	}

	private void writeEmployeeToFile(Employee employee) throws IOException {
		Files.createDirectories(Paths.get(outputDir));

		String fileName = "employee_" + employee.getName() + "_" + System.currentTimeMillis() + ".txt";
		Path filePath = Paths.get(outputDir, fileName);

		StringBuilder contnet = new StringBuilder();
		contnet.append("Name :: ").append(employee.getName()).append(" Salary :: ").append(employee.getSalary());

		Files.writeString(filePath, contnet, StandardOpenOption.CREATE);

		log.info("Employee details are stored in file");

	}

//	public Employee update(Long id, Employee employee) {
//		log.info("Updating Employee with employeeId :: " + id);
//		Employee existingEmployee = 
//		existingEmployee.setName(employee.getName());
//		existingEmployee.setDepartment(employee.getDepartment());
//		existingEmployee.setSalary(employee.getSalary());
//
//		return employeeRepository.save(existingEmployee);
//	}

//	public void delete(Long id) {
//		log.info("Deleting Employee with employeeId :: " + id);
//		employeeRepository.delete(findById(id));
//	}
}
