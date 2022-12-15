package com.example.application.views.list;

import com.example.application.data.Match;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@PageTitle("list")
@Route(value = "")
//@Route(value = "login", layout = LoginView.class)

public class ListView extends VerticalLayout implements RouterLayout {
    Navbar nav = new Navbar();
    DatePicker datePicker = new DatePicker();

    public ListView() throws IOException, InterruptedException {
        setSpacing(false);
        setPadding(false);

        TextField searchField = new TextField();
        searchField.getElement().setAttribute("aria-label", "search");
        searchField.setPlaceholder("Search");
        searchField.setClearButtonVisible(true);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());

        ArrayList<Match> matches = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.cup2022.ir/api/v1/match")).setHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzhhNjBmNWYyODA5NGFhM2E5YzI4YjAiLCJpYXQiOjE2NzAwMTM3NjcsImV4cCI6MTY3MDEwMDE2N30.42mfwHPEr8ZGoe7boREZM-k2evSrXa5shMJc05wb8Zk")
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject test123 = Json.parse(response.body());

        JsonArray arr = test123.getArray("data");
        String holder = "";
        for (int i=0 ; i<arr.length(); i++) {
            String time = arr.getObject(i).getString("local_date").split(" ")[1];
            String date = arr.getObject(i).getString("local_date").split(" ")[0];
            String flag1 = arr.getObject(i).getString("home_flag");
            String flag2 = arr.getObject(i).getString("away_flag");

            String homeTeam = arr.getObject(i).getString("home_team_en");
            String awayTeam = arr.getObject(i).getString("away_team_en");
            String score = (int)arr.getObject(i).getNumber("home_score") + "-" + (int)arr.getObject(i).getNumber("away_score");
            Match tempMatch = new Match(time,date,score,homeTeam,awayTeam,flag1, flag2);
            matches.add(tempMatch);
        }

        AtomicReference<MatchesView> matchesView = new AtomicReference<>(new MatchesView(matches, "", ""));

        Button reset = new Button("Reset");


        HorizontalLayout mainButtons = new HorizontalLayout(searchField, reset, datePicker);

        setWidthFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

        reset.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        datePicker.addValueChangeListener(value -> {
            matchesView.set(new MatchesView(matches, value.getValue().toString(), searchField.getValue().toString()));
            removeAll();

            add(nav, mainButtons, matchesView.get());
        });

        searchField.addValueChangeListener(value -> {
            matchesView.set(new MatchesView(matches, datePicker.getValue().toString(), searchField.getValue().toString()));

            removeAll();

            add(nav, mainButtons, matchesView.get());
        });


        reset.addClickListener(buttonClickEvent -> {
            datePicker.setValue(LocalDate.now());
            searchField.setValue("");

            matchesView.set(new MatchesView(matches,"", "" ));
            removeAll();

            add(nav, mainButtons, matchesView.get());
        });

        mainButtons.setClassName("PageButtons");


        add(nav, mainButtons, matchesView.get());
    }

//    public ListView() throws IOException, InterruptedException {
//        setSpacing(false);
//        setPadding(false);
//
//        add(new LoginView());
//    }

}
