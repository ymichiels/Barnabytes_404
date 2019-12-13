package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class League {
    
    private StringProperty leagueName;
    private ChampionshipDay[] championshipDay;
    private Team[] team;
    
    
    public League(String leagueName, ChampionshipDay[] championshipDay, Team[] team) {
            this.leagueName = new SimpleStringProperty(leagueName);
            this.championshipDay = championshipDay;
            this.team = team;
    }

    public League(String leagueName, ChampionshipDay[] championshipDay) {
        this.leagueName = new SimpleStringProperty(leagueName);
        this.championshipDay = championshipDay;
    }
    
    public League(League league) {
            this.leagueName = league.leagueNameProperty();
            this.championshipDay = null;
            this.team = null;
    }
    
    public String getLeagueName() {
        return leagueName.get();
    }
    
    public void setLeagueName(String leagueName) {
        this.leagueName.set(leagueName);
    }
    
    public StringProperty leagueNameProperty() {
        return leagueName;
    }
}
