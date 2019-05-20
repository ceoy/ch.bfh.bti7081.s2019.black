package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.security.exceptions.AccessDeniedException;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.*;

import javax.servlet.http.HttpServletResponse;

@Route("error")
@PageTitle("FORBIDDEN")
public class AccessDeniedView extends Div implements HasErrorParameter<AccessDeniedException> {

    public AccessDeniedView() {
        H1 h1 = new H1("access denied / error");
        this.add(h1);
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent beforeEnterEvent, ErrorParameter<AccessDeniedException> errorParameter) {
        return HttpServletResponse.SC_FORBIDDEN;
    }

}

