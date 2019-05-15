package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.api;

import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.business.EmployeeService;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.view.dtos.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeApi {
    @Autowired
    EmployeeService employeeService;

    public EmployeeDto findByAppointmentId(long id) {
        return employeeService.findByAppointmentId(id);
    }
}
