package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.business;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess.AppointmentRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.assembler.AppointmentAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.model.Task;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentAssembler appointmentAssembler;

    public List<AppointmentDto> findAll() {
        return appointmentAssembler.toDtos(appointmentRepository.findAll());
    }

    public AppointmentDto findById(long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        return appointmentOptional.map(appointment -> appointmentAssembler.toDto(appointment)).orElse(null);

    }

    public List<AppointmentDto> findByEmployee(long employeeId) {
        return appointmentAssembler.toDtos(appointmentRepository.findByEmployeeId(employeeId));
    }

    public void add(TaskDto taskDto, long appointmentId) {
        Appointment appointment = appointmentRepository.getOne(appointmentId);
        appointment.getTasks().add(new Task(taskDto, appointment));
        appointmentRepository.save(appointment);
    }
}
