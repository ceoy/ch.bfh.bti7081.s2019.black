package ch.bfh.bti7081.s2019.black.spitexorganizer.admin.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.security.Role;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.access.annotation.Secured;

@Route(value = "admin", layout = MainLayout.class)
@PageTitle("Admin")
@Secured(Role.ADMIN)
public class AdminView extends VerticalLayout {

    public AdminView() {
        H1 h1 = new H1("This is an admin-only page");
        this.add(h1);
    }
}
