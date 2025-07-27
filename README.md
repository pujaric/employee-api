# employee-api
A simple Employee API with GET, POST, PUT, DELETE using Guice + Spring Boot 3 + MyBatis + H2

```
   ┌──────────────┐
   │ Spring Boot  │
   └────┬─────────┘
        │
        ▼
Spring Scans Components
        │
        ├── @MapperScan → registers EmployeeMapper
        ├── Loads GuiceConfig
        │        │
        │        ├── receives EmployeeMapper bean
        │        └── creates Guice Injector with AppModule
        │                  │
        │                  ├── binds EmployeeMapper to instance
        │                  └── binds EmployeeService to Impl
        ▼
 employeeService bean exposed via @Bean
        │
        ▼
Injected into Spring Controller
```

# H2 console access

```
http://localhost:8080/h2-console
Use JDBC URL: jdbc:h2:mem:employees
```

# Hit the api
You can use Postman or curl:

```
curl -X POST http://localhost:8080/employees \
     -H "Content-Type: application/json" \
     -d '{"name":"Chandra", "email":"chandra@example.com"}'
```
# Use Injector bridge + Spring context lookup
Instead of passing all mappers manually, let Guice fetch Spring beans dynamically using a bridge. It avoids

```
public AppModule(EmployeeMapper employeeMapper, UserMapper userMapper) {
    ...
}
```
Step-by-Step: Dynamic Guice → Spring Bean Bridge
1. Define a Spring-aware Guice Provider (SpringBeanProvider) ✅ 
2. Modify AppModule to use dynamic provider ✅ 
3. Update GuiceConfig to pass ApplicationContext ✅ 

Bean Creation Flow Summary

```
Spring Boot starts 
	➝ MyBatis mappers registered by @Mapper 
		➝ GuiceModule reads Spring context 
			➝ Guice binds beans 
				➝ Service layer managed by Guice 
					➝ Guice-created services bridged back to Spring 
						➝ Controllers get injected services via Spring
```

## 🔍 MockMvc vs TestRestTemplate in Spring Boot Tests

```
| Feature              | `MockMvc`                               | `TestRestTemplate`                   |
|----------------------|-----------------------------------------|--------------------------------------|
| Starts Web Server    | ❌ No                                   | ✅ Yes (embedded Tomcat/Jetty/etc.)  |
| Speed                | ⚡ Faster                                | 🐢 Slower (due to full HTTP stack)   |
| Full-stack Testing   | ❌ No (bypasses filters, servlet layer) | ✅ Yes (real servlet context)        |
| Real HTTP Layer      | ❌ No                                   | ✅ Yes                               |
| Test Complexity      | ✅ Easier for unit-style                | 🧪 Closer to real-world behavior     |

### ✅ When to Use What?

- **Use `MockMvc`** when:
  - You want fast, lightweight controller tests.
  - You don't need actual network/servlet layer.

- **Use `TestRestTemplate`** when:
  - You need full-stack integration testing.
  - You want to test filters, security, headers, cookies, etc.
  - You simulate real HTTP client behavior.

---

> 💡 Both are useful depending on your testing goal. It's common to use `MockMvc` for controller-level unit tests and `TestRestTemplate` for integration tests.
```
