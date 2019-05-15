package ch.bfh.bti7081.s2019.black.spitexorganizer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;


public class MainLayout extends Div implements RouterLayout {
    public MainLayout() {
        this.addClassName("container");

        UI.getCurrent().getPage().addStyleSheet("style/skeleton.min.css");
        UI.getCurrent().getPage().addStyleSheet("style/normalize.min.css");
        UI.getCurrent().getPage().addStyleSheet("style/custom.css");

        // create menu
        NavigationMenu navMenu = new NavigationMenu();

        add(navMenu);
    }
}