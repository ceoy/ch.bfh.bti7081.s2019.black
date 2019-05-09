package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess.AppointmentRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {
    private final static Logger logger = Logger.getLogger(MainView.class.getName());

    @Autowired
    private AppointmentRepository appointmentRepository;


    public MainView() {
        Button button = new Button("Click me TEST  WORKS!!!!!!",
                event -> Notification.show("Clicked!"));
        add(button);
        List<Appointment> appointments = appointmentRepository.findAll();
        logger.log(Level.SEVERE, appointments.toString());
        Grid<Appointment> grid = new Grid<>();
        grid.setItems(appointments);
        grid.addColumn(Appointment::getId).setHeader("ID");
        grid.addColumn(Appointment::getStart).setHeader("START");
        add(grid);
    }
}
