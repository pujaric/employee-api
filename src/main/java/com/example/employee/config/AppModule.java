package com.example.employee.config;

import org.springframework.context.ApplicationContext;

import com.example.employee.dao.EmployeeMapper;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.EmployeeServiceImpl;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

    private final ApplicationContext context;

    public AppModule(ApplicationContext context) {
        this.context = context;
    }

    @Override
    protected void configure() {
        bind(EmployeeMapper.class).toProvider(new SpringBeanProvider<>(context, EmployeeMapper.class));
//        bind(UserMapper.class).toProvider(new SpringBeanProvider<>(context, UserMapper.class));
        
        // Add more if needed...
        bind(EmployeeService.class).to(EmployeeServiceImpl.class);
//        bind(UserService.class).to(UserServiceImpl.class);
    }
}
