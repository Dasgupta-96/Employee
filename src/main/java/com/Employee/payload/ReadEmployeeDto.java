package com.Employee.payload;

import com.Employee.Entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadEmployeeDto {

    private Employee emp;
    private String message;

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Employee getEmp() {
        return emp;
    }

    public String getMessage() {
        return message;
    }
}
