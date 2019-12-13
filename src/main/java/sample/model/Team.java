package sample.model;

import java.io.Serializable;
//import java.util.ArrayList;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
//import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class Team extends RecursiveTreeObject<Team> implements Serializable {

    private int teamId;
    private StringProperty category = null;
    public IntegerProperty rank;
    public IntegerProperty point;
    public IntegerProperty played;
    public IntegerProperty won;
    public IntegerProperty equality;
    public IntegerProperty lost;
    public IntegerProperty goalSet;
    public IntegerProperty goalConceded;
    public IntegerProperty goalAverage;
    public StringProperty teamName;
    private String coach;
    //private final ArrayList<Player> playersList;
    //private final ObjectProperty<ObservableList<Player>> playersList;
    private ListProperty<Player> playersList;
    
    public Team(String teamName, String category) {
        this.teamName = new SimpleStringProperty(teamName);
        this.teamId = 1;
        this.category = new SimpleStringProperty(category);
        this.point = new SimpleIntegerProperty(89);
        this.playersList = new SimpleListProperty<>();
    }

    public Team(String teamName) {
        this.teamName = new SimpleStringProperty(teamName);
    }

    public Team(Integer teamId, String teamName, String coach) {
        this.teamId = teamId;
        this.teamName = new SimpleStringProperty(teamName);
        this.rank = new SimpleIntegerProperty(0);
        this.point = new SimpleIntegerProperty(0);
        this.played = new SimpleIntegerProperty(0);
        this.won = new SimpleIntegerProperty(0);
        this.equality = new SimpleIntegerProperty(0);
        this.equality = new SimpleIntegerProperty(0);
        this.lost = new SimpleIntegerProperty(0);
        this.coach = coach;
    }

    public Team(Integer teamId, String teamName, int point, int played, int won, int equality, int lost,
                int goalSet, int goalConceded, int goalAverage, String coach) {
        this.teamId = teamId;
        this.teamName = new SimpleStringProperty(teamName);
        this.rank = new SimpleIntegerProperty(0);
        this.point = new SimpleIntegerProperty(point);
        this.played = new SimpleIntegerProperty(played);
        this.won = new SimpleIntegerProperty(won);
        this.equality = new SimpleIntegerProperty(equality);
        this.lost = new SimpleIntegerProperty(lost);
        this.goalSet = new SimpleIntegerProperty(goalSet);
        this.goalConceded = new SimpleIntegerProperty(goalConceded);
        this.goalAverage = new SimpleIntegerProperty(goalAverage);
        this.coach = coach;
    }


//    public Team(Team team) {
//        super.clubId = super.clubIdProperty();
//        super.clubName = super.clubNameProperty();
//        super.city = super.cityProperty();
//        this.teamId = team.teamIdProperty();
//        this.category = team.categoryProperty();
//        this.point = team.pointProperty();
//        this.playersList = team.playersListProperty();
//    }

    public void addPlayer (Player player) {
        if (playersList.isEmpty())
            playersList.add(player);
        else {
            if (!(playersList.contains(player))) {
                int number = player.getNumber();
                int ind = 0;
                while (ind < playersList.size() && playersList.get(ind).getNumber() < number) {
                    ind++;
                }
                playersList.add(ind, player);
            }
        }
    }

    public void addPlayer (String firstName, String lastName, int number, boolean goalKeeper) {
        Player player = new Player(firstName, lastName);
        addPlayer(player);
    }

    public void removePlayer (Player player) {
        if(player != null) {
            playersList.remove(player);
        }
    }

    public ObservableList<Player> getPlayersList() {
        return playersList.get();
    }
    
    public final void setPlayersList(ObservableList<Player> value) {	
        playersList.set(value);
    }
    
    public final ListProperty<Player> playersListProperty() {		
        return playersList;
    }
    
    public String getCategory()
    {
        return category.get();
    }
    
    public StringProperty categoryProperty()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category.set(category);
    }

    public int getIdTeam() {
        return teamId;
    }
    
//    public IntegerProperty teamIdProperty() {
//        return teamId;
//    }

    public String getTeamName() {
        return teamName.get();
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }
    
    public Integer getPoint() {
        return point.get();
    }
    
    public IntegerProperty pointProperty() {
        return point;
    }
    
    public void setPoint(Integer point) {
        this.point.set(point);
    }
    
    public Player findPlayer(String firstName, String lastName) {
        int ind;

        for (ind = 0; ind < playersList.size(); ind++) {
            Player player = playersList.get(ind);
            if (player.getFirstName().equalsIgnoreCase(firstName) && player.getLastName().equalsIgnoreCase(lastName)) {
                return player;
            }
        }
        return null;
    }

    public String getCoach()
    {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}
