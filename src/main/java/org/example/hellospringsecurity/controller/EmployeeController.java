package org.example.hellospringsecurity.controller;

import org.example.hellospringsecurity.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @GetMapping("/api/v1/employees")
    public List<Employee> getEmployees(){
        return List.of(
                new Employee(1L,"Nguyen Van A",100.0),
                new Employee(2L,"Nguyen Van B",200.0),
                new Employee(3L,"Nguyen Van C",500.0)
        );
    }
}
