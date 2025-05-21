package uz.hojiakbar.newprogect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.hojiakbar.newprogect.Entity.Employee;
import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e Where e.name = :name ")
    List<Employee> findAll(@Param("name") String name);
    List<Employee> findAllByName(String name);
    List<Employee> findAllByNameStartingWith(String name);
    Employee findByName(String name);

    @Query("Select e from Employee e where Upper(e.name) like upper(concat(:name, '%'))")
    List<Employee> findByNameLikeJPQL(@Param("name") String name);

    @Query(value = "Select e from employee e where e.name like :name", nativeQuery = true)
    List<Employee> findByNameLikeNative(@Param("name") String name);
}
