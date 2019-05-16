package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.business;

import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.dataaccess.EmployeeRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.view.assembler.EmployeeAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.view.dtos.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeAssembler employeeAssembler;

    public EmployeeDto findByAppointmentId(long id) {
        return employeeAssembler.toDto(employeeRepository.findByAppointmentId(id));
    }
}
