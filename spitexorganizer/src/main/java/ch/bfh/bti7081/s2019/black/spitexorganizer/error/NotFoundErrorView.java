package ch.bfh.bti7081.s2019.black.spitexorganizer.error;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import javax.servlet.http.HttpServletResponse;

@Tag("div")
@PageTitle("404: Nichts gefunden")
public class NotFoundErrorView extends VerticalLayout
        implements HasErrorParameter<NotFoundException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {

        UI.getCurrent().getPage().addStyleSheet("style/skeleton.min.css");
        UI.getCurrent().getPage().addStyleSheet("style/normalize.min.css");
        UI.getCurrent().getPage().addStyleSheet("style/custom.css");

        this.setClassName("container");

        H1 title = new H1("Nichts gefunden");
        Paragraph description = new Paragraph("Die Seite konnte leider nicht gefunden werden :(");


        this.add(title);
        this.add(description);


        return HttpServletResponse.SC_NOT_FOUND;
    }
}