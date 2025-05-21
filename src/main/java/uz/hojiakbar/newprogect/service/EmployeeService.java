package uz.hojiakbar.newprogect.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.hojiakbar.newprogect.Entity.Employee;
import uz.hojiakbar.newprogect.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee save(Employee employee) {
        if (employee.getId() != null) {
            Employee existing = employeeRepository.findById(employee.getId())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id " + employee.getId()));
            existing.setName(employee.getName());
            existing.setLastName(employee.getLastName());
            existing.setEmail(employee.getEmail());
            return employeeRepository.save(existing);
        } else {
            return employeeRepository.save(employee);
        }
    }

    @Transactional
    public Employee findById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
    @Transactional
    public List<Employee> findAll(String name){
        List<Employee> employees = employeeRepository.findAllByName(name);
        return employees;
    }
    @Transactional
    public List<Employee> findByQueryParam(String name){
        return employeeRepository.findByNameLikeJPQL(name);
    }

    @Transactional
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

}
