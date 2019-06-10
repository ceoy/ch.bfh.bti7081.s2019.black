package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.assembler;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.view.assembler.EmployeeAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.assembler.PatientAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.assembler.ReportAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.assembler.TaskAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentAssembler {

    @Autowired
    TaskAssembler taskAssembler;

    @Autowired
    PatientAssembler patientAssembler;

    @Autowired
    ReportAssembler reportAssembler;

    @Autowired
    EmployeeAssembler employeeAssembler;

    public List<AppointmentDto> toDtos(List<Appointment> appointments) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointments) {
            appointmentDtos.add(toDto(appointment));
        }
        return appointmentDtos;
    }

    public AppointmentDto toDto(Appointment appointment) {
        AppointmentDto appointmentDto = toReportDto(appointment);
        appointmentDto.setReport(reportAssembler.toDto(appointment.getReport()));
        return appointmentDto;
    }

    public AppointmentDto toReportDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setStart(appointment.getStart());
        appointmentDto.setEnd(appointment.getEnd());
        appointmentDto.setTasks(taskAssembler.toDtos(appointment.getTasks()));
        appointmentDto.setPatient(patientAssembler.toDto(appointment.getPatient()));

        return appointmentDto;
    }
}
