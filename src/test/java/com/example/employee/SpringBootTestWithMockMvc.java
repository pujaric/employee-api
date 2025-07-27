package com.example.employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // If your test resources folder contains applicaiton-test.yml file.
@AutoConfigureMockMvc // Inject MockMvc for API tests
public class SpringBootTestWithMockMvc {

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc; // Simulate HTTP calls to controller
	
	@Value("${my.environment:'this is default value'}")
    private String environment;

	@Test
    void shouldReturnUsersFromDatabase() throws Exception {
		System.out.println("Loaded environment: " + environment);
        mockMvc.perform(get("/users"))
        	.andDo(print()) // prints the actual JSON to console
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].NAME").value("Chandra"))
            .andExpect(jsonPath("$[1].NAME").value("Sekhar"));
    }
}
