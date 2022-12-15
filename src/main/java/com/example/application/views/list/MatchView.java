package com.example.application.views.list;

import com.example.application.data.Match;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;

public class MatchView extends HorizontalLayout {
    H2 homeHeader;
    H2 awayHeader;
    H3 time;
    H3 score;
    Avatar homeAvatar = new Avatar("Home Avatar");
    Avatar awayAvatar = new Avatar("Away Avatar");

    public  MatchView(Match match){
        setClassName("match");
        homeHeader = new H2(match.getHomeTeam());
        homeAvatar.setImage(match.getFlag1());
        homeAvatar.setClassName("avatar");
        awayAvatar.setClassName("avatar");
        time = new H3(match.getDate());
        score = new H3(match.getScore());
        VerticalLayout centerLayout = new VerticalLayout(score, time);
        centerLayout.addClassName("matchCenter");
        centerLayout.setAlignItems(Alignment.CENTER);
        awayAvatar.setImage(match.getFlag2());
        awayHeader = new H2(match.getAwayTeam());
        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        add(homeHeader, homeAvatar, centerLayout, awayAvatar, awayHeader);
    }
}
