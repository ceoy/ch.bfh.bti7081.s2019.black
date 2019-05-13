package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.AppointmentView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.TestView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;



@ParentLayout(MainLayout.class)
public class NavigationMenu extends Div implements RouterLayout {

    HorizontalLayout menu = new HorizontalLayout();
    HorizontalLayout buttonContainer = new HorizontalLayout();
    public NavigationMenu(){
        addMenuElement(AppointmentView.class, "Appointments");
        addMenuElement(TestView.class, "TEST");
        buttonContainer.getStyle().set("margin", "auto");
        menu.add(buttonContainer);
        add(menu);
    }

    private void addMenuElement(Class<? extends Component> navigationTarget,
                                String name) {
        Button button = new Button(name);
        button.addClickListener(e -> {
            UI.getCurrent().navigate(navigationTarget);
        });
        button.addThemeName("tertiary");
        buttonContainer.add(button);
    }


}
