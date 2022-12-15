package com.example.application.views.list;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("app-layout-navbar-placement")
// tag::snippet[]
public class AppLayoutNavbarPlacement extends AppLayout {

    public AppLayoutNavbarPlacement() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Hakathon");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "20px");
        TextField textField = new TextField();
        textField.getElement().setAttribute("aria-label", "search");
        textField.setPlaceholder("Search");
        textField.setClearButtonVisible(true);
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        Tabs tabs = getTabs();
        this.setClassName("navbarTabs");

//        addToDrawer(tabs);

        addToNavbar(title, textField);

    }
    // end::snippet[]

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.DASHBOARD, "Dashboard"),
                createTab(VaadinIcon.CART, "Orders"),
                createTab(VaadinIcon.USER_HEART, "Customers"),
                createTab(VaadinIcon.PACKAGE, "Products"),
                createTab(VaadinIcon.RECORDS, "Documents"),
                createTab(VaadinIcon.LIST, "Tasks"),
                createTab(VaadinIcon.CHART, "Analytics"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        // Demo has no routes
        // link.setRoute(viewClass.java);
        link.setTabIndex(-1);

        return new Tab(link);
    }

      // tag::snippet[]
}
// end::snippet[]
