package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.business.AppointmentService;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentApi {

    @Autowired
    AppointmentService appointmentService;

    public List<AppointmentDto> findAll() {
        return appointmentService.findAll();
    }

    public AppointmentDto findById(long id) {
        return appointmentService.findById(id);
    }

    public List<AppointmentDto> findByEmployee(long employeeId) {
        return appointmentService.findByEmployee(employeeId);
    }

    public List<AppointmentDto> findByPatientId(long id) {
        return appointmentService.findByPatientId(id);
    }
}
