package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.business.AppoinmentService;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentApi {

    @Autowired
    AppoinmentService appoinmentService;

    public List<AppointmentDto> findAll(){
        return appoinmentService.findAll();
    }
}
