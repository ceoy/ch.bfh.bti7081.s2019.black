package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.security.exceptions.AccessDeniedException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.*;

import javax.servlet.http.HttpServletResponse;

@Route("error")
@PageTitle("FORBIDDEN")
public class AccessDeniedView extends Div implements HasErrorParameter<AccessDeniedException> {

    public AccessDeniedView() {

        this.setClassName("container");

        UI.getCurrent().getPage().addStyleSheet("style/skeleton.min.css");
        UI.getCurrent().getPage().addStyleSheet("style/normalize.min.css");
        UI.getCurrent().getPage().addStyleSheet("style/custom.css");

        H1 title = new H1("Kein Zugriff");
        Paragraph description = new Paragraph("Sie haben keinen Zugriff auf diese Seite.");


        this.add(title);
        this.add(description);
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent beforeEnterEvent, ErrorParameter<AccessDeniedException> errorParameter) {
        return HttpServletResponse.SC_FORBIDDEN;
    }

}

