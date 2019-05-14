package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "", layout = MainLayout.class)
public class AppointmentView extends VerticalLayout implements RouterLayout {

    public AppointmentView(@Autowired AppointmentApi appointmentApi) {
        Button button = new Button("Click me TEST  WORKS!!!!!!",
                event -> Notification.show("Clicked!"));
        add(button);
        List<AppointmentDto> appointmentDtos = appointmentApi.findAll();
        Grid<AppointmentDto> grid = new Grid<>();
        grid.setItems(appointmentDtos);
        grid.addColumn(AppointmentDto::getId).setHeader("ID");
        grid.addColumn(AppointmentDto::getStart).setHeader("START");

        grid.addItemClickListener(appointmentDtoItemClickEvent ->
                UI.getCurrent().navigate(AppointmentDetailView.class, appointmentDtoItemClickEvent.getItem().getId())
        );

        add(grid);

        NativeButton nativeButton = new NativeButton("LEAVE MAIN");
        nativeButton.addClickListener(e -> {
            nativeButton.getUI().ifPresent(ui -> ui.navigate("test"));
        });
        add(nativeButton);

        // create and add a fuckton of buttons so i can see the navbar in action :)
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
        add(new NativeButton("LEAVE MAIN"));
    }
}
