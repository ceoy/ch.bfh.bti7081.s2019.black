package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.view.assembler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.model.Employee;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.view.dtos.EmployeeDto;

@Component
public class EmployeeAssembler {
  public List<EmployeeDto> toDtos(List<Employee> employees){
    List<EmployeeDto> employeeDtos=new ArrayList<>();
    for (Employee employee:employees){
      employeeDtos.add(toDto(employee));
    }
    return employeeDtos;
  }

  public EmployeeDto toDto(Employee employee){
    EmployeeDto employeeDtos = new EmployeeDto();
    employeeDtos.setId(employee.getId());
    employeeDtos.setUsername(employee.getUsername());
    return employeeDtos;
  }
}
