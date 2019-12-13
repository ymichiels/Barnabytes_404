package sample.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class Match {

    private static final String SPACE = "        ";
    private static final String PREFIX = "( ";
    private static final String POSTFIX = " )";

    private Player player;
    private IntegerProperty id;
    private Team team1;
    private int teamId1;
    private String coachNameTeam1;
    private String teamName1;
    private int scoreTeam1;
    private Team team2;
    private int teamId2;
    private String coachNameTeam2;
    private String teamName2;
    private int scoreTeam2;
    private Referee referee;
    private String refereeName;
    private ObjectProperty<LocalDate> date;
    //private ChampionshipDay championshipDay;
    //private League league;
    private String league;
    private BooleanProperty start;
    private Boolean startMatch = false;
    private ArrayList<Action> actions;
    private ArrayList<Player> players;
    private int strikeCountTeam1;
    private int strikeCountTeam2;
    private int passCountTeam1;
    private int passCountTeam2;
    private int foulCountTeam1;
    private int foulCountTeam2;
    private int newEnterCountTeam1;
    private int newEnterCountTeam2;
    private int newOutCountTeam1;
    private int newOutCountTeam2;
    private int yellowCardCountTeam1;
    private int yellowCardCountTeam2;
    private int redCardCountTeam1;
    private int redCardCountTeam2;
    private int blueCardCountTeam1;
    private int blueCardCountTeam2;
    private int saveCountTeam1;
    private int saveCountTeam2;
    private int deadTimeCountTeam1;
    private int deadTimeCountTeam2;
    private int exclusionCountTeam1;
    private int exclusionCountTeam2;
    private int warningCountTeam1;
    private int warningCountTeam2;

    public Match(int id, int teamId1, String teamName1, int scoreTeam1,  int teamId2,
                 String teamName2, int scoreTeam2, ArrayList<Player> players, ArrayList<Action> actions) {
        this.id = new SimpleIntegerProperty(id);
        this.teamId1 = teamId1;
        this.teamName1 = teamName1;
        this.scoreTeam1 = scoreTeam1;
        this.teamId2 = new Integer(teamId2);
        this.teamName2 = teamName2;
        this.scoreTeam2 = scoreTeam2;
        this.players = players;
        this.actions = new ArrayList<Action>();
    }

    public Match(int id, String refereeName,
                 int teamId1, String teamName1, String coachNameTeam1, int scoreTeam1,
                 int teamId2, String teamName2, String coachNameTeam2, int scoreTeam2,
                 boolean startMatch, ArrayList<Action> actions) {
        this.id = new SimpleIntegerProperty(id);
        this.refereeName = refereeName;
        this.teamId1 = teamId1;
        this.coachNameTeam1 = coachNameTeam1;
        this.teamName1 = teamName1;
        this.scoreTeam1 = scoreTeam1;
        this.teamId2 = teamId2;
        this.coachNameTeam2 = coachNameTeam2;
        this.teamName2 = teamName2;
        this.scoreTeam2 = scoreTeam2;
        this.startMatch = startMatch;
        this.actions = actions;
    }

    public Match(int id, Team team1, int scoreTeam1, Team team2, int scoreTeam2, 
                    Referee referee, LocalDate date, ChampionshipDay championshipDay, boolean start) {
        this.id = new SimpleIntegerProperty(id);
        this.team1 = team1;
        this.scoreTeam1 = scoreTeam1;
        this.team2 = team2;
        this.scoreTeam2 = scoreTeam2;
        this.referee = referee;
        this.date = new SimpleObjectProperty(date);
        //this.championshipDay = championshipDay;
        this.start = new SimpleBooleanProperty(false);
        this.actions = new ArrayList<Action>(null);
    }

//    public Match(Team team1, int scoreTeam1, Team team2, int scoreTeam2, Boolean startMatch, ArrayList<Action> actions) {
//        this.team1 = new Team(null, teamId1, null);
//        this.scoreTeam1 = scoreTeam1;
//        this.team2 = new Team(null, teamId2, null);
//        this.scoreTeam2 = scoreTeam2;
//        this.startMatch = startMatch;
//        this.actions = actions;
//    }

    public void newGoal(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            scoreTeam1++;
            strikeCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            scoreTeam2++;
            strikeCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName,SPACE+timer, "but", player.getValue().getLastName()));
    }
    public void newFoul(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            foulCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            foulCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName,SPACE+timer, "faute", player.getValue().getLastName()));
    }
    public void newStrike(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            strikeCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            strikeCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName,SPACE+timer, "tir", player.getValue().getLastName()));
    }
    public void newEnter(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            newEnterCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            newEnterCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "entrée", player.getValue().getLastName()));
    }
    public void newOut(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            newOutCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            newOutCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "sortie", player.getValue().getLastName()));
    }
    public void newPass(TreeItem<Player> player, String timer) {
        String teamName = "";
        if (player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            passCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            passCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "passe décisive", player.getValue().getLastName()));
    }
    public void newDeadTime(TreeItem<Player> player, String timer) {
        String teamName = "";
        String coachName = "";
        if (player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            coachName = this.getCoachNameTeam1();
            deadTimeCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            coachName = this.getCoachNameTeam2();
            deadTimeCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "temps mort", coachName));
    }
    public void newExclusion(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            exclusionCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            exclusionCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "exclusion", player.getValue().getLastName()));
    }
    public void newWarning(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            warningCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            warningCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "avertissement", player.getValue().getLastName()));
    }
    public void newYellowCard(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            yellowCardCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            yellowCardCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "carton jaune", player.getValue().getLastName()));
    }
    public void newRedCard(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            redCardCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            redCardCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "carton rouge", player.getValue().getLastName()));
    }
    public void newBlueCard(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            blueCardCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            blueCardCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "carton bleu", player.getValue().getLastName()));
    }
    public void newSave(TreeItem<Player> player, String timer) {
        String teamName = "";
        if(player.getValue().getId() == this.getIdTeam1()) {
            teamName = this.getNameTeam1();
            saveCountTeam1++;
        }
        else {
            teamName = this.getNameTeam2();
            saveCountTeam2++;
        }
        actions.add(new Action(this.getId(), player.getValue().getId(), teamName, SPACE+timer, "arrêt", player.getValue().getLastName()));
    }


    public int getId() {
        return id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }

    public Team getTeam1() {
        return team1;
    }
    public void setTeam1(Team team1) {
        this.team1 = team1;
    }
    public int getIdTeam1() {
        return teamId1;
    }
    public void setIdTeam1(int idTeam1) {
        this.teamId1 = teamId1;
    }
    public String getNameTeam1() {
        return teamName1;
    }
    public void setNameTeam1(String teamName1) {
        this.teamName1 = teamName1;
    }
    public int getScoreTeam1() {
        return scoreTeam1;
    }
    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1= scoreTeam1;
    }
    public String getCoachNameTeam1() {
        return coachNameTeam1;
    }
    public void setCoachNameTeam1(String coachNameTeam1) {
        this.coachNameTeam1 = coachNameTeam1;
    }

    public Team getTeam2() {
        return team2;
    }
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
    public int getIdTeam2() {
        return teamId2;
    }
    public void setIdTeam2(int idTeam2) {
        this.teamId2 = teamId2;
    }
    public String getNameTeam2() {
        return teamName2;
    }
    public void setNameTeam2(String teamName2) {
        this.teamName2 = teamName2;
    }
    public int getScoreTeam2() {
        return scoreTeam2;
    }
    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }
    public String getCoachNameTeam2() {
        return coachNameTeam2;
    }
    public void setCoachNameTeam2(String coachNameTeam2) {
        this.coachNameTeam2 = coachNameTeam2;
    }

    public int getStrikeCountTeam1() {
        return strikeCountTeam1;
    }
    public int getStrikeCountTeam2() {
        return strikeCountTeam2;
    }

    public int getPassCountTeam1() {
        return passCountTeam1;
    }
    public int getPassCountTeam2() {
        return passCountTeam2;
    }

    public int getFoulCountTeam1() {
        return foulCountTeam1;
    }
    public int getFoulCountTeam2() {
        return foulCountTeam2;
    }

    public int getNewEnterCountTeam1() {
        return newEnterCountTeam1;
    }
    public int getNewEnterCountTeam2() {
        return newEnterCountTeam2;
    }

    public int getNewOutCountTeam1() {
        return newOutCountTeam1;
    }
    public int getNewOutCountTeam2() {
        return newOutCountTeam2;
    }

    public int getYellowCardCountTeam1() {
        return yellowCardCountTeam1;
    }
    public int getYellowCardCountTeam2() {
        return yellowCardCountTeam2;
    }

    public int getRedCardCountTeam1() {
        return redCardCountTeam1;
    }
    public int getRedCardCountTeam2() {
        return redCardCountTeam2;
    }

    public int getBlueCardCountTeam1() {
        return blueCardCountTeam1;
    }
    public int getBlueCardCountTeam2() {
        return blueCardCountTeam2;
    }

    public int getSaveCountTeam1() {
        return saveCountTeam1;
    }
    public int getSaveCountTeam2() {
        return saveCountTeam2;
    }

    public int getExclusionCountTeam1() {
        return exclusionCountTeam1;
    }
    public int getExclusionCountTeam2() {
        return exclusionCountTeam2;
    }

    public int getDeadTimeCountTeam1() {
        return deadTimeCountTeam1;
    }
    public int getDeadTimeCountTeam2() {
        return deadTimeCountTeam2;
    }


//    public ChampionshipDay getDay() {
//        return championshipDay;
//    }
//    public void setDay(ChampionshipDay championshipDay) {
//        this.championshipDay = championshipDay;
//    }

    public boolean isStarted() {
        return start.get();
    }
    public BooleanProperty isStartedProperty() {
        return start;
    }
    public void setStarted(boolean start) {
        this.start.set(start);
    }

    public LocalDate getDate() {
        return date.get();
    }
    public void setDate(LocalDate date) {
        this.date.set(date);
    }
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }
//    public ArrayList<Action> getActionsProperty() { return actions; }
    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
