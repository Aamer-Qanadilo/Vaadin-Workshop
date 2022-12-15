package com.example.application.data;

import com.example.application.data.entity.AbstractEntity;

import javax.validation.constraints.NotEmpty;
import java.net.URL;

public class Match extends AbstractEntity {
    @NotEmpty
    private String time;



    @NotEmpty
    private String date;

    @NotEmpty
    private String score;

    @NotEmpty
    private String homeTeam;

    @NotEmpty
    private String awayTeam;


    @NotEmpty
    private String flag1;

    @NotEmpty
    private String flag2;


    public Match(String time,String date, String score, String homeTeam, String awayTeam, String flag1, String flag2) {
        this.awayTeam = awayTeam;
        String []splitter = date.split("/");
        String Swapper = splitter[0];
        splitter[0] = splitter[2];
        splitter[2] = Swapper;
        Swapper = splitter[1];
        splitter[1] = splitter[2];
        splitter[2] = Swapper;
        this.date = splitter[0] + '-' + splitter[1] + '-' + splitter[2];
        this.time = time;
        this.score = score;
        this.flag2 = flag2;
        this.flag1 = flag1;
        this.homeTeam = homeTeam;
    }

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime(){
        return this.time;
    }

    public String setTime(String day){
        return this.time=day;
    }

    public void setScore(String score){
        this.score=score;
    }

    public String getScore(){
        return this.score;
    }

    public String getHomeTeam(){
        return this.homeTeam;
    }

    public void setHomeTeam(String homeTeam){
        this.homeTeam=homeTeam;
    }

    public String getAwayTeam(){
        return this.awayTeam;
    }

    public void setAwayTeam(String awayTeam){
        this.homeTeam=awayTeam;
    }
}
