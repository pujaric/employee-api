package com.example.employee.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.employee.service.EmployeeService;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Configuration
public class GuiceConfig {

    private final Injector injector;

    public GuiceConfig(ApplicationContext context) {
        this.injector = Guice.createInjector(new AppModule(context));
    }

    @Bean
    public EmployeeService employeeService() {
        return injector.getInstance(EmployeeService.class);
    }

//    @Bean
//    public UserService userService() {
//        return injector.getInstance(UserService.class);
//    }
}






