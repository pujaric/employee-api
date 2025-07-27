package com.example.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.example.employee.model.Employee;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
//@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-test.yml")
public class SpringBootTestWithTestRestTemplate {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql(statements = "INSERT INTO employee(name, email) VALUES('abc', 'abc@mail.com')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM employee WHERE name='abc'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetProducts() {
		List<Employee> employees = restTemplate.getForObject("/employees", List.class);
		assertEquals(3, employees.size());
	}
}