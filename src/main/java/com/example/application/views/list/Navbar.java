package com.example.application.views.list;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

public class Navbar extends HorizontalLayout {

    public Navbar() {
        Avatar headerAvatar = new Avatar("World Cup");
        H1 header = new H1("Hakathon World Cup");
        headerAvatar.setImage("images/world-cup.png");
        this.setWidthFull();
        this.addClassName("navbar");
        this.setAlignItems(Alignment.CENTER);

        add(header, headerAvatar);
    }
}
