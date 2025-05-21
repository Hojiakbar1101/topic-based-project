package uz.hojiakbar.newprogect.Web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.hojiakbar.newprogect.Entity.Employee;
import uz.hojiakbar.newprogect.service.EmployeeService;

@RestController
@RequestMapping("/api")

public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/employee")
    public ResponseEntity create(@RequestBody Employee employee) {
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }
}
