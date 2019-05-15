package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.NavigationMenu;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "", layout = NavigationMenu.class)
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class AppointmentView extends VerticalLayout implements RouterLayout {
    private final static Logger logger = Logger.getLogger(AppointmentView.class.getName());


    public AppointmentView(@Autowired AppointmentApi appointmentApi) {
        Button button = new Button("Click me TEST  WORKS!!!!!!",
                event -> Notification.show("Clicked!"));
        add(button);
        List<AppointmentDto> appointmentDtos = appointmentApi.findAll();
        Grid<AppointmentDto> grid = new Grid<>();
        grid.setItems(appointmentDtos);
        grid.addColumn(AppointmentDto::getId).setHeader("ID");
        //grid.addColumn(AppointmentDto::getName).setHeader("Name");
        grid.addColumn(AppointmentDto::getStart).setHeader("START");
        add(grid);
        NativeButton nativeButton = new NativeButton("LEAVE MAIN");
        nativeButton.addClickListener( e-> {
            nativeButton.getUI().ifPresent(ui -> ui.navigate("test"));
        });
        add(nativeButton);
    }
}
