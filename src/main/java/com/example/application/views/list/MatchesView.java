package com.example.application.views.list;

import com.example.application.data.Match;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;

public class MatchesView extends VerticalLayout {

    public MatchesView(ArrayList<Match> matches, String date, String name) {
        setClassName("matchParent");
        setWidthFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        if((date == null || date.length() == 0) && (name == null || name.length() == 0)) {
            matches.forEach(match -> add(new MatchView(match)));
        } else if ((date == null || date.length() == 0) && (name.length() > 0)){
            removeAll();
            matches.forEach(match -> {
                if(match.getAwayTeam().toLowerCase().contains(name.toLowerCase()) || match.getHomeTeam().toLowerCase().contains(name.toLowerCase()) ) {
                    add(new MatchView(match));
                }
            });
        } else if ((date.length() > 0) && (name == null || name.length() == 0)) {
            removeAll();
            matches.forEach(match -> {
                if(match.getDate().equals(date)) {
                    add(new MatchView(match));
                }
            });
        }
        else {
            removeAll();
            matches.forEach(match -> {
                if((match.getAwayTeam().toLowerCase().contains(name.toLowerCase())
                        || match.getHomeTeam().toLowerCase().contains(name.toLowerCase()) )
                        && match.getDate().equals(date) ) {
                    add(new MatchView(match));
                }
            });
        }
    }

}
