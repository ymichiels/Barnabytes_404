package sample.model;

import javafx.beans.property.StringProperty;

public class Action {

    protected int id;
    protected int playerId;
    protected int matchId;
    protected int count;
    protected Team team;
    protected String timer;
    protected String action;
    protected String player;
    protected String teamName;
    protected String referee;
    protected String coach;

    public Action(int matchId, int playerId, String teamName, String timer, String action, String player) {
        this.timer = timer;
        this.action = action;
        this.player = player;
        this.teamName = teamName;
    }

    public Action(int matchId, int playerId, String teamName, String timer, String action, String player, int count) {
        this.timer = timer;
        this.action = action;
        this.player = player;
        this.teamName = teamName;
        this.count = count;
    }

    public Action(int id, int matchId, int playerId, String teamName, String timer, String action, String player) {
        this.id = id;
        this.timer = timer;
        this.action = action;
        this.player = player;
        this.teamName = teamName;
    }

    public Action(int id, String referee, String action, String timer) {
        this.id = id;
        this.referee = referee;
        this.action = action;
        this.timer = timer;
    }

//    public Action(int id, int matchId, int playerId, Team team, String timer, String action, Player player) {
//        this.id = id;
//        this.timer = timer;
//        this.action = action;
//        this.player = String.valueOf(new Player(player));
//        this.team = new Team(teamName);
//    }
//
    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) { this.timer = timer; }

    public String getAction() {
        return action;
    }

    public void setAction(String action) { this.action= action; }

    //public StringProperty actionProperty() { return action; }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) { this.player = player; }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) { this.referee = referee; }

    //public StringProperty playerProperty() { return player; }

    public String getTeamName() { return teamName; }

    public void setTeamName(String teamName) { this.teamName= teamName; }

    //public StringProperty teamNameProperty() { return teamName; }

    public int getIdPlayer() {
        return playerId;
    }

    public void setIdPlayer(int playerId) {
        this.playerId = playerId;
    }

    public int getIdMatch() {
        return matchId;
    }

    public void setIdMatch(int matchId) {
        this.matchId = matchId;
    }
}
