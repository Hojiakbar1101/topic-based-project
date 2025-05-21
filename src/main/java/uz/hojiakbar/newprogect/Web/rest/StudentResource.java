package uz.hojiakbar.newprogect.Web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.hojiakbar.newprogect.model.Course;
import uz.hojiakbar.newprogect.model.Student;
import uz.hojiakbar.newprogect.Entity.Employee;
import uz.hojiakbar.newprogect.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResource {

    private final EmployeeService employeeService;

    public StudentResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/Students")
    public ResponseEntity create(@RequestBody Student student) {
        return ResponseEntity.ok(student);
    }

    @PutMapping("/Students/{id}")
    public ResponseEntity updateSecond(@PathVariable long id, @RequestBody Student student) {
        student.setId(id);
        student.setSurname("Test uchun");
        return ResponseEntity.ok(student);
    }

    @PostMapping("/Students/list")
    public ResponseEntity createList(@RequestBody List<Student> students) {
        return ResponseEntity.ok(students);
    }

    @GetMapping("/Students/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Course course = new Course(2L, "Backend", "Spring Boot course", 12);
        Student student = new Student(id, "Hojiakbar", "Saidrasulov", 20, course);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/Students")
    public ResponseEntity getAll(@RequestParam(required = false) Long id,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String surname,
                                 @RequestParam(required = false) Integer age) {
        List<Student> students = new ArrayList<>();
        Course course = new Course();
        course.setId(2L);
        course.setName("Test course");
        students.add(new Student(id, name, surname, age, course));
        students.add(new Student(3L, "name", "surname", 43, course));
        return ResponseEntity.ok("Hello world from Student");
    }

    @PatchMapping("/Students/{id}")
    public ResponseEntity patchUpdate(@PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.ok(student);
    }

    @PutMapping("/employees")
    public ResponseEntity<?> update(@RequestBody Employee employee) {
        Employee result = employeeService.save(employee);
        if (employee.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/employees")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Employee result = employeeService.findById(id);
        return ResponseEntity.ok(result);

    }
    /*@GetMapping("/employees")
    public ResponseEntity getAll(@RequestParam String name){
        List<Employee>  employees = employeeService.findAll(name);
        return ResponseEntity.ok(employees);
    }*/
    @GetMapping("/employees/search")
    public ResponseEntity findByQueryParam(@RequestParam String name){
        List<Employee>  result = employeeService.findByQueryParam(name);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        employeeService.delete(id);
       return  ResponseEntity.ok("Qator o'chdi");
    }
}
