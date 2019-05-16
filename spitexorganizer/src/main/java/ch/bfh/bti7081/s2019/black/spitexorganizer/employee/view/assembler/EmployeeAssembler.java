package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.view.assembler;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.assembler.AppointmentAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.model.Employee;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.view.dtos.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeAssembler {

    @Autowired
    AppointmentAssembler appointmentAssembler;

    public List<EmployeeDto> toDtos(List<Employee> employees) {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDtos.add(toDto(employee));
        }
        return employeeDtos;
    }

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDtos = new EmployeeDto();
        employeeDtos.setId(employee.getId());
        employeeDtos.setUsername(employee.getUsername());
        employeeDtos.setName(employee.getName());
        employeeDtos.setSurname(employee.getSurname());
        employeeDtos.setMail(employee.getMail());
        employeeDtos.setAppointments(appointmentAssembler.toDtos(employee.getAppointments()));
        return employeeDtos;
    }
}
