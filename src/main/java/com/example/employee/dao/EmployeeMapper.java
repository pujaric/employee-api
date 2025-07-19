package com.example.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.employee.model.Employee;

@Mapper
public interface EmployeeMapper {

	@Select("SELECT * FROM employee")
	List<Employee> getAll();

	@Select("SELECT * FROM employee WHERE id = #{id}")
	Employee getById(int id);

	@Insert("INSERT INTO employee(name, email) VALUES(#{name}, #{email})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(Employee employee);

	@Update("UPDATE employee SET name=#{name}, email=#{email} WHERE id=#{id}")
	void update(Employee employee);

	@Delete("DELETE FROM employee WHERE id=#{id}")
	void delete(int id);
}
