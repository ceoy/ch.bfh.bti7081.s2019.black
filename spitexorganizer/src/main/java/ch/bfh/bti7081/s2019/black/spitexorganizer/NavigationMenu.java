package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.AppointmentDetailView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.AppointmentView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.TestView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.ui.EvaluationListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.RepeatIndex;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.ArrayList;
import java.util.List;

@Tag("navigation-menu")
@HtmlImport("frontend://src/NavigationMenu.html")
public class NavigationMenu extends PolymerTemplate<NavigationMenu.NavigationMenuModel> {

    private List<MenuItem> menuItems = new ArrayList<>();

    public NavigationMenu() {
        // add elements
        menuItems.add(new MenuItem(AppointmentView.class, "Wochenplanung"));
        menuItems.add(new MenuItem(EvaluationListView.class, "Evaluation"));

        getModel().setMenu(menuItems);
    }

    @EventHandler
    private void handleMenuClick(@RepeatIndex int index) {
        // navigate
        UI.getCurrent().navigate(menuItems.get(index).target);
    }

    private void createTestButton() {
        Button button = new Button("Test Appointemnt Detail");
        button.addClickListener(e -> UI.getCurrent().navigate(AppointmentDetailView.class, 1L));

        // buttonContainer.add(button);
    }

    public interface NavigationMenuModel extends TemplateModel {
        @Include({"name"})
        void setMenu(List<MenuItem> menu);
    }

    public class MenuItem {
        private Class<? extends Component> target;
        private String name;

        public MenuItem(Class<? extends Component> target, String name) {
            this.target = target;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
