package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.dataaccess;

import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
