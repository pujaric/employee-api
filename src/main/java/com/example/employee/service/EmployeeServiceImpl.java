package com.example.employee.service;

import java.util.List;

import com.example.employee.dao.EmployeeMapper;
import com.example.employee.model.Employee;
import com.google.inject.Inject;

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeMapper mapper;

	@Inject // Guice will inject this
	public EmployeeServiceImpl(EmployeeMapper mapper) {
		this.mapper = mapper;
	}

	public List<Employee> getAll() {
		return mapper.getAll();
	}

	public Employee getById(int id) {
		return mapper.getById(id);
	}

	public void create(Employee emp) {
		mapper.insert(emp);
	}

	public void update(Employee emp) {
		mapper.update(emp);
	}

	public void delete(int id) {
		mapper.delete(id);
	}
}
