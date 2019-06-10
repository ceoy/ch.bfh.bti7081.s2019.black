package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.api.PatientApi;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Autowired;


public class MainLayout extends Div implements RouterLayout {

    public MainLayout(@Autowired PatientApi patientApi) {
        this.addClassName("container");

        UI.getCurrent().getPage().addStyleSheet("style/skeleton.min.css");
        UI.getCurrent().getPage().addStyleSheet("style/normalize.min.css");
        UI.getCurrent().getPage().addStyleSheet("style/custom.css");

        // create menu
        NavigationMenu navMenu = new NavigationMenu(patientApi);

        add(navMenu);
    }
}