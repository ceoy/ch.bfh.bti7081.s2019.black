package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.AppointmentView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.security.SecurityUtil;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("login-view")
@HtmlImport("frontend://src/Login.html")
@PageTitle("LoginView")
@Route(value = "login")
public class LoginView extends PolymerTemplate<TemplateModel> implements BeforeEnterObserver {

    public LoginView() {
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (SecurityUtil.isUserLoggedIn()) {
            // Needed manually to change the URL because of https://github.com/vaadin/flow/issues/4189
            UI.getCurrent().getPage().getHistory().replaceState(null, "");
            event.rerouteTo(AppointmentView.class);
        }
    }
}
