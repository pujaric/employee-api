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
