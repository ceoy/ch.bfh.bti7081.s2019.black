package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.dataaccess;

import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM EMPLOYEE WHERE ID IN (SELECT a.EMPLOYEE_ID FROM APPOINTMENT AS a WHERE a.ID = ?)")
    Employee findByAppointmentId(long id);
}
