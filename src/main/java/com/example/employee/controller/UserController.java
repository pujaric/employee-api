package com.example.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping
	public List<Map<String, Object>> getAllUsers() {
		return jdbcTemplate.queryForList("SELECT * FROM users");
	}
}
