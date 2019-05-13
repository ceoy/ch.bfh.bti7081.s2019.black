package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
}
