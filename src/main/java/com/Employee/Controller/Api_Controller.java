package com.Employee.Controller;

import com.Employee.EmployeeApplication;
import com.Employee.Entity.Employee;
import com.Employee.Exception.ResourceNotFoundException;
import com.Employee.Repository.EmployeeRepository;
import com.Employee.payload.ReadEmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saveData")
public class Api_Controller {

    // http://localhost:8080/api/saveData
    EmployeeRepository employeeRepo;

    public Api_Controller(EmployeeRepository employeeRepo) {

        this.employeeRepo = employeeRepo;
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody Employee e1) {

        Employee emp = employeeRepo.save(e1);
        ReadEmployeeDto dto = new ReadEmployeeDto();

        dto.setEmp(emp);
        dto.setMessage("The record is saved!!");


        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/particular")
    // http://localhost:8080/api/saveData/particular?id=3
    public ResponseEntity<?> displayEmployee(@RequestParam("id") int id) {

        Employee emp = employeeRepo.findById(id).orElseThrow(

                () -> new ResourceNotFoundException("The data is not present in this id " + id)
        );


        return new ResponseEntity<>(emp, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {

        employeeRepo.deleteById(id);

        return new ResponseEntity<>("The data is deleted!!", HttpStatus.OK);
    }

    @GetMapping // pagination and sorting
//  http://localhost:8080/api/saveData?pageNo=0&pageSize=3
    public ResponseEntity<?> getAllData(

            @RequestParam(name="pageNo", required = false,defaultValue = "0") int pageNo,
        @RequestParam(name="pageSize",required = false, defaultValue = "3") int pageSize
    ){
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Employee> pages = employeeRepo.findAll(pageable);
        List<Employee> result = pages.getContent();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
