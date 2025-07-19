package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.dao.EmployeeMapper;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

//	private final EmployeeService service;

//	private final EmployeeMapper mapper;

//	@Inject // Guice will inject this
//	public EmployeeController(EmployeeService service, EmployeeMapper mapper) {
//		this.service = service;
//		this.mapper = mapper;
//	}
	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private EmployeeMapper mapper;

	@GetMapping
	public List<Employee> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public Employee getById(@PathVariable int id) {
		return service.getById(id);
	}

	@PostMapping
	public void create(@RequestBody Employee emp) {
		service.create(emp);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable int id, @RequestBody Employee emp) {
		emp.setId(id);
		service.update(emp);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
	}
}
