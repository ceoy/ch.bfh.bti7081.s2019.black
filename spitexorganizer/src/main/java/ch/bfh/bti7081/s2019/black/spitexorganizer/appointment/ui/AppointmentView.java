package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.ui.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
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
        // set a title to make it obvious where we are.
        H1 title = new H1("Wochenplan");
        
        List<AppointmentDto> appointmentDtos = appointmentApi.findAll();

        // create the grid and populate it
        Grid<AppointmentDto> grid = new Grid<>();
        grid.setItems(appointmentDtos);
        grid.addColumn(appointmentDto -> appointmentDto.getPatient().getName() + " " + appointmentDto.getPatient().getSurname()).setHeader("Patienten Name:").setWidth("200px");
        grid.addColumn(appointmentDto -> appointmentDto.getDate()).setHeader("Datum");
        grid.addColumn(appointmentDto -> appointmentDto.getStartTime()).setHeader("Von:");
        grid.addColumn(appointmentDto -> appointmentDto.getEndTime()).setHeader("Bis:");

        // navigate to the detail view when clicking on an entry
        grid.addItemClickListener(appointmentDtoItemClickEvent ->
                UI.getCurrent().navigate(AppointmentDetailView.class, appointmentDtoItemClickEvent.getItem().getId())
        );

        this.add(title);
        this.add(grid);
    }
}
