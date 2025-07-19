package com.example.employee.service;

import java.util.List;

import com.example.employee.model.Employee;

public interface EmployeeService {
	List<Employee> getAll();

	Employee getById(int id);

	void create(Employee emp);

	void update(Employee emp);

	void delete(int id);
}
