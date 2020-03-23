package com.gcp.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.backend.model.Employee;
import com.gcp.backend.repositories.EmployeeRepository;

/**
 * @author M1052874
 *
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	public String test() {
		return "Spring boot";
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);

		System.out.println("get method 1");
		return ResponseEntity.ok().body(employee);
		
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		/*
		 * employee=new Employee(); employee.setEmailId("amrishwork100@gmail.com");
		 * employee.setFirstName("Amrish"); employee.setLastName("Kumar");
		 */
		System.out.println("post method 1");
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) {
		Optional<Employee> employee = employeeRepository.findById(employeeDetails.getId());

		/*
		 * employee.setEmailId(employeeDetails.getEmailId());
		 * employee.setLastName(employeeDetails.getLastName());
		 * employee.setFirstName(employeeDetails.getFirstName());
		 */
		System.out.println("put method 1");
		Employee updatedEmployee = employeeRepository.save(employeeDetails);
		return new ResponseEntity(updatedEmployee, HttpStatus.CREATED);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Employee employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId.getId());

		employeeRepository.delete(employeeId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		System.out.println("delete method 1");
		return response;
	}
}