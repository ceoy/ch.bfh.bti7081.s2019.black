package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("test")
public class TestView extends VerticalLayout {

    public TestView(@Autowired AppointmentApi appointmentApi) {
        Button button = new Button("BACK TO MAIN");
        button.addClickListener( e-> {
            button.getUI().ifPresent(ui -> ui.navigate(""));
        });
        add(button);
    }
}
