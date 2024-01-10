package com.Employee.Controller;

import com.Employee.Entity.Employee;
import com.Employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/getData")
public class ApiController {
    EmployeeRepository employeeRepo;

    public ApiController(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping
    public  List<Employee> getData(){
       List<Employee> result = employeeRepo.findAll();

       return result;
    }
    @PostMapping
public ResponseEntity<?> saveData(@RequestBody Employee e1){

        employeeRepo.save(e1);

        return new ResponseEntity<>("The record is saved!!", HttpStatus.CREATED);
}
// http://localhost:8080/api/getData

}
