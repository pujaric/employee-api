//package com.example.employee.config;
//
//import org.springframework.context.ApplicationContext;
//
//import com.google.inject.Provider;
//
//public class SpringBeanProvider<T> implements Provider<T> {
//
//	private final ApplicationContext context;
//	private final Class<T> beanClass;
//
//	public SpringBeanProvider(ApplicationContext context, Class<T> beanClass) {
//		this.context = context;
//		this.beanClass = beanClass;
//	}
//
//	@Override
//	public T get() {
//		return context.getBean(beanClass);
//	}
//}
