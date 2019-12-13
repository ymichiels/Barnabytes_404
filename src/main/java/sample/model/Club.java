package sample.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Club extends RecursiveTreeObject<Team>  implements Serializable {
    
    private static final long serialVersionUID = 0L;

    public IntegerProperty clubId;
    public StringProperty clubName;
    protected StringProperty city;
    protected ListProperty<Team> teamsList;

    public Club(int clubId, String clubName, String city, ArrayList<Team> teamsList) {
        this.clubId = new SimpleIntegerProperty(clubId);
        this.clubName = new SimpleStringProperty(clubName);
        this.city = new SimpleStringProperty(city);
        this.teamsList = new SimpleListProperty<>();
    }
    
    public Club(String clubName) {
        this.clubName = new SimpleStringProperty(clubName);
    }


    public Club(Club club) {
        this.clubId = club.clubIdProperty();
        this.clubName = club.clubNameProperty();
        this.city = club.cityProperty();
        this.teamsList = club.teamsListProperty();
    }

    public void addTeam (String name, String category) {
        Team Team = new Team(name, category);
    }

    public void removeTeam (Team team) {
        if(team != null) {
            teamsList.remove(team);
        }
    }

    public ObservableList<Team> getTeamsList() {
        return teamsList.get();
    }
    
    public void setTeamsList(ObservableList<Team> value) {
        teamsList.set(value);
    }
    
    public ListProperty<Team> teamsListProperty() {
        return teamsList;
    }
    
    public int getClubId() {
        return clubId.get();
    }
    
    public IntegerProperty clubIdProperty() {
        return clubId;
    }

    public String getClubName() {
        return clubName.get();
    }
    
    public void setClubName(String clubName) {
        this.clubName.set(clubName);
    }
    
    public StringProperty clubNameProperty() {
        return clubName;
    }
    
    public String getCity() {
        return city.get();
    }
    
    public void setCity(String city) {
        this.city.set(city);
    }
    
    public StringProperty cityProperty() {
        return city;
    }

    /*public Team findTeam(int teamId, String category) {
        int ind;

        for (ind = 0; ind < teamsList.size(); ind++) {
            Team team = teamsList.get(ind);
            if (team.getId() && team.getCategory().equalsIgnoreCase(category)) {
                return team;
            }
        }
        return null;
    }*/
}
