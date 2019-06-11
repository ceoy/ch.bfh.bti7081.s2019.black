package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.admin.ui.AdminView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.AppointmentView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.ui.EvaluationListView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.api.PatientApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.security.SecurityUtil;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.RepeatIndex;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Tag("navigation-menu")
@HtmlImport("frontend://src/NavigationMenu.html")
public class NavigationMenu extends PolymerTemplate<NavigationMenu.NavigationMenuModel> {

    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    public NavigationMenu(@Autowired PatientApi patientApi) {

        List<PatientDto> allPatients = patientApi.findAll();
        int counter = 0;
        for (PatientDto allPatient : allPatients) {
            if (allPatient.getEvaluationDue()) {
                counter++;
            }
        }

        // add elements
        menuItems.add(new MenuItem(AppointmentView.class, "Wochenplanung"));
        menuItems.add(new MenuItem(EvaluationListView.class, "Evaluation", counter));

        if (SecurityUtil.isUserLoggedIn()) {

            if (SecurityUtil.isAccessGranted(AdminView.class)) {
                menuItems.add(new MenuItem(AdminView.class, "Admin"));
            }

            // add a menu item that will log you out.
            menuItems.add(new SpecialMenuItem("Logout", () ->
                    UI.getCurrent().getPage().executeJavaScript("location.assign('logout')")));
        }

        getModel().setMenu(menuItems);
    }

    @EventHandler
    private void handleMenuClick(@RepeatIndex int index) {
        // navigate
        MenuItem menuItem = menuItems.get(index);
        if (menuItem.getClass().equals(SpecialMenuItem.class)) {
            ((SpecialMenuItem) menuItem).getAction().run();
        }
        UI.getCurrent().navigate(menuItems.get(index).target);
    }

    public interface NavigationMenuModel extends TemplateModel {
        @Include({"name", "notificationCount"})
        void setMenu(List<MenuItem> menu);
    }

    public class MenuItem {

        private Class<? extends Component> target;
        private String name;
        private Integer notificationCount;

        public MenuItem(Class<? extends Component> target, String name, Integer notificationCount) {
            this.target = target;
            this.name = name;
            this.notificationCount = notificationCount;
        }

        public MenuItem(Class<? extends Component> target, String name) {
            this.target = target;
            this.name = name;
            this.notificationCount = 0;
        }

        public String getName() {
            return name;
        }

        public Integer getNotificationCount() {
            return notificationCount;
        }
    }

    public class SpecialMenuItem extends MenuItem {
        private Runnable action;

        public SpecialMenuItem(String name, Runnable action) {
            super(Component.class, name);
            this.action = action;
        }

        public Runnable getAction() {
            return action;
        }
    }

}
