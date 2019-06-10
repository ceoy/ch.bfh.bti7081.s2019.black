package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.api.PatientApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

/**
 * The evaluation overview is just a list of all patients and their last done evaluation.
 */
@Route(value = "evaluation/overview", layout = MainLayout.class)
@PageTitle("Evaluationsübersicht")
public class EvaluationListView extends VerticalLayout implements RouterLayout {

    public EvaluationListView(@Autowired PatientApi patientApi) {
        List<PatientDto> patientDtos = patientApi.findAll();

        H1 title = new H1("Evaluationsübersicht");

        // order the list
        patientDtos.sort(Comparator.comparing(PatientDto::getLastEvaluation));

        // create the grid
        Grid<PatientDto> grid = new Grid<>();
        grid.setItems(patientDtos);
        grid.addColumn(patientDto -> patientDto.getName() + " " + patientDto.getSurname()).setHeader("Offene Evaluationen");
        grid.addColumn(PatientDto::getFormattedLastEvaluation).setHeader("Letzte Evaluation");

        grid.addItemClickListener(clickedPatient -> {
            List<EvaluationDto> evaluations = clickedPatient.getItem().getEvaluations();
            EvaluationDto evaluationDto = evaluations.get(evaluations.size() - 1);
            UI.getCurrent().navigate(EvaluationViewCreate.class, evaluationDto.getId());
        });

        this.add(title);
        this.add(grid);
    }
}