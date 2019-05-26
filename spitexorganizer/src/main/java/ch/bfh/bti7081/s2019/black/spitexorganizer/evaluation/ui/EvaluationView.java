package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.api.EvaluationApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import java.util.List;



@Route(value = "evaluation-view", layout = MainLayout.class)
public class EvaluationView extends VerticalLayout implements RouterLayout {
	
	public EvaluationView(@Autowired EvaluationApi evaluationApi) {
		
        List<EvaluationDto> evaluationDtos = evaluationApi.findAll();
        Grid<EvaluationDto> grid = new Grid<>();
        grid.setItems(evaluationDtos);
        grid.addColumn(evaluationDto -> evaluationDto.getPatient().getName() + " " + evaluationDto.getPatient().getSurname()).setHeader("Patienten Name:").setWidth("200px");
        grid.addColumn(evaluationDto -> evaluationDto.getPatient().getName()).setHeader("Name");
        grid.addColumn(EvaluationDto::getText).setHeader("Text");
        
        //grid.addColumn(evaluationDto -> evaluationDto.getStartTime()).setHeader("Von:");
        //grid.addColumn(evaluationDto -> evaluationDto.getEndTime()).setHeader("Bis:");

        add(grid);

        NativeButton nativeButton = new NativeButton("LEAVE MAIN");
        nativeButton.addClickListener(e -> {
            nativeButton.getUI().ifPresent(ui -> ui.navigate("test"));
        });
        add(nativeButton);
		
		
	}
}
