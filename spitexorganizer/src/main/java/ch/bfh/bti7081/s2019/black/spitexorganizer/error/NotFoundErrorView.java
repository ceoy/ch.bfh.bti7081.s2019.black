package ch.bfh.bti7081.s2019.black.spitexorganizer.error;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.*;

import javax.servlet.http.HttpServletResponse;

@Tag("div")
@PageTitle("404: Nichts gefunden")
public class NotFoundErrorView extends Component
        implements HasErrorParameter<NotFoundException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {
        getElement().setText(parameter.getCustomMessage());
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
