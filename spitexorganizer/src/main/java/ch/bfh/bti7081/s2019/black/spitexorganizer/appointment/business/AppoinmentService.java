package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.business;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess.AppointmentRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.assembler.AppointmentAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppoinmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentAssembler appointmentAssembler;

    public List<AppointmentDto> findAll(){
        return appointmentAssembler.toDtos(appointmentRepository.findAll());
    }
}
