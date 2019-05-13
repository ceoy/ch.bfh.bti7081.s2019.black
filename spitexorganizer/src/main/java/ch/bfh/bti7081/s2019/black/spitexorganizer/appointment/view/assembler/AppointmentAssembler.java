package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.assembler;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.assembler.TaskAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentAssembler {

    @Autowired
    TaskAssembler taskAssembler;

    public List<AppointmentDto> toDtos(List<Appointment> appointments){
        List<AppointmentDto> appointmentDtos=new ArrayList<>();
        for (Appointment appointment:appointments){

            appointmentDtos.add(toDto(appointment));
        }
        return appointmentDtos;
    }

    private AppointmentDto toDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setStart(appointment.getStart());
        appointmentDto.setEnd(appointment.getEnd());
        appointmentDto.setTasks(taskAssembler.toDtos(appointment.getTasks()));
        return appointmentDto;
    }
}
