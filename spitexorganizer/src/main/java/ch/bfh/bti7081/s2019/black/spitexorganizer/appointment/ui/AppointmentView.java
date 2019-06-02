package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
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

        // fetch data for the grid
        // todo: only display appointments for the current week (for dates that have not been passed).
        List<AppointmentDto> appointmentDtos = appointmentApi.findAll();

        // create grid and fill
        Grid<AppointmentDto> grid = new Grid<>();
        grid.setItems(appointmentDtos);
        grid.addColumn(appointmentDto -> appointmentDto.getPatient().getName() + " " + appointmentDto.getPatient().getSurname()).setHeader("Patienten Name:").setWidth("200px");
        grid.addColumn(appointmentDto -> appointmentDto.getDate()).setHeader("Datum");

        grid.addColumn(appointmentDto -> appointmentDto.getStartTime()).setHeader("Von:");
        grid.addColumn(appointmentDto -> appointmentDto.getEndTime()).setHeader("Bis:");


        grid.addItemClickListener(appointmentDtoItemClickEvent ->
                UI.getCurrent().navigate(AppointmentDetailView.class, appointmentDtoItemClickEvent.getItem().getId())
        );

        // add the elements to the view
        this.add(title);
        this.add(grid);
    }
}
