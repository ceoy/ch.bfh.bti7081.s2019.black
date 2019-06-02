package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.api.PatientApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The main view contains a button and a click listener.
 */

@Route(value = "EvaluationList", layout = MainLayout.class)
public class EvaluationListView extends VerticalLayout implements RouterLayout {

    public EvaluationListView(@Autowired PatientApi patientApi) {

        
        List<PatientDto> patientDtos = patientApi.findAll();
        Grid<PatientDto> grid = new Grid<>();
        grid.setItems(patientDtos);
        grid.addColumn(patientDto -> patientDto.getName() + " " + patientDto.getSurname()).setHeader("Offene Evaluationen").setWidth("200px");
        grid.addColumn(patientDto -> patientDto.getLastEvaluation()).setHeader("Letzte Evaluation:");
        
        

        grid.addItemClickListener(evaluationDtoItemClickEvent ->
                UI.getCurrent().navigate(EvaluationDetailView.class, evaluationDtoItemClickEvent.getItem().getId())
        );
        
        add(grid);

        NativeButton nativeButton = new NativeButton("Back to Main View");
        nativeButton.addClickListener(e -> {
            nativeButton.getUI().ifPresent(ui -> ui.navigate(""));
        });
        add(nativeButton);

        // create and add a fuckton of buttons so i can see the navbar in action :)
        //add(new NativeButton("LEAVE MAIN"));
    }
}