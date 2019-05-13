package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.AppointmentDetailView;
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

    private HorizontalLayout buttonContainer = new HorizontalLayout();

    public NavigationMenu() {
        // create menu
        HorizontalLayout menu = new HorizontalLayout();

        // add elements
        addMenuElement(AppointmentView.class, "Wochenplanung");
        addMenuElement(TestView.class, "Evaluation");
        createTestButton();

        // add button container to menu
        menu.add(buttonContainer);

        // style button container
        buttonContainer.getStyle().set("margin", "auto");

        // add menu to navigation menu
        add(menu);
    }

    private void createTestButton() {
        Button button = new Button("Test Appointemnt Detail");
        button.addClickListener(e -> UI.getCurrent().navigate(AppointmentDetailView.class, 1L));

        button.addThemeName("tertiary");

        buttonContainer.add(button);
    }

    private void addMenuElement(Class<? extends Component> navigationTarget,
                                String name) {
        // create a button
        Button button = new Button(name);

        // add click listener for navigation
        button.addClickListener(e -> UI.getCurrent().navigate(navigationTarget));

        // add style
        button.addThemeName("tertiary");

        // add button to the button container
        buttonContainer.add(button);
    }

}
