package com.medooy.spring.rest;

import com.medooy.spring.rest.configuration.MyConfig;
import com.medooy.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        List<Employee> allEmployees = communication.showAllEmployees();
        System.out.println(allEmployees);
        Employee empById = communication.getEmployee(4);
        System.out.println(empById);
        Employee employee = new Employee("Sveta", "Sokolova", "IT", 600);
        employee.setId(20);
        communication.saveEmployee(employee);

        communication.deleteEmployee(18);
    }
}
