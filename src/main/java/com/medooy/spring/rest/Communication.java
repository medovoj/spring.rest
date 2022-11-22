package com.medooy.spring.rest;

import com.medooy.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/spring_rest/api/employees";

    public List<Employee> showAllEmployees(){
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});
        return responseEntity.getBody();
    }

    public Employee getEmployee(int id){
        return restTemplate.getForObject(url + "/" + id, Employee.class);
    }

    public void saveEmployee(Employee employee){
        int id = employee.getId();
        if (id == 0){
            ResponseEntity<String > responseEntity = restTemplate.postForEntity(url, employee, String.class);
            System.out.println("New Employee was added to database");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(url, employee);
            System.out.println("Employee with ID="+ id +" was updated to database");

        }

    }

    public void deleteEmployee(int id){
        restTemplate.delete(url + "/" + id);
        System.out.println("Employee with ID="+ id +" was deleted from database");

    }
}
