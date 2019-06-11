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

import java.util.List;

/**
 * The evaluation overview is just a list of all patients and their last done evaluation.
 */
@Route(value = "evaluation/overview", layout = MainLayout.class)
@PageTitle("Evaluationsübersicht")
public class EvaluationListView extends VerticalLayout implements RouterLayout {

    public EvaluationListView(@Autowired PatientApi patientApi) {
        List<PatientDto> patientDtos = patientApi.findWhereEvaluationDue();

        H1 title = new H1("Evaluationsübersicht");

        // order the list
        patientDtos.sort((o1, o2) -> {
            EvaluationDto o1LastEval = o1.getLastEvaluation();
            EvaluationDto o2LastEval = o2.getLastEvaluation();

            // god damn beautiful..
            if ((o1LastEval == null && o2LastEval == null)) {
                return 0;
            } else if (o1LastEval == null) {
                return -1;
            } else if (o2LastEval == null) {
                return 1;
            } else if (o1LastEval.getSent() == null && o2LastEval.getSent() == null) {
                return 0;
            } else if (o1LastEval.getSent() == null) {
                return -1;
            } else if (o2LastEval.getSent() == null) {
                return 1;
            } else {
                return o1LastEval.getSent().compareTo(o2LastEval.getSent());
            }
        });

        // create the grid
        Grid<PatientDto> grid = new Grid<>();
        grid.setItems(patientDtos);
        grid.addColumn(patientDto -> patientDto.getName() + " " + patientDto.getSurname()).setHeader("Offene Evaluationen");
        grid.addColumn(PatientDto::getFormattedLastEvaluation).setHeader("Letzte Evaluation");

        grid.addItemClickListener(clickedPatient -> {
            List<EvaluationDto> evaluations = clickedPatient.getItem().getEvaluations();
            EvaluationDto eval = evaluations.get(evaluations.size() - 1);
            UI.getCurrent().navigate(EvaluationViewCreate.class, eval.getId());

        });

        this.add(title);
        this.add(grid);
    }
}