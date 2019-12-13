package sample.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Player extends RecursiveTreeObject<Player> {

    public StringProperty firstName;
    public StringProperty lastName;
    protected int id;
    protected SimpleIntegerProperty numberLicence;
    public SimpleIntegerProperty number;
    protected StringProperty post;
    public String state;
    protected StringProperty lastClub;
    public SimpleIntegerProperty numberGoal;
    public SimpleIntegerProperty numberYellowCard;
    public SimpleIntegerProperty numberRedCard;
    public SimpleIntegerProperty numberBlueCard;
    public SimpleIntegerProperty numberSave;
    protected BooleanProperty goalKeeper;
    protected BooleanProperty pro;
    protected Team team;

    public Player() {
        this(null, null);
    }

    public Player(String firstName, String lastName) {
        //super(firstName, lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        // Some initial dummy data, just for convenient testing.
        this.id = 1;
        this.numberLicence = new SimpleIntegerProperty(55452);
        this.post = new SimpleStringProperty("Guardien");
        this.team = new Team("Pau", "senior");
        this.lastClub = new SimpleStringProperty("Bordeaux");
        this.number = new SimpleIntegerProperty(7);
        this.numberGoal = new SimpleIntegerProperty(0);
        this.numberSave = new SimpleIntegerProperty(6);
        this.goalKeeper = new SimpleBooleanProperty(true);
        this.pro = new SimpleBooleanProperty(true);
    }

    public Player(int id, String post, int number, String lastName) {
        this.id = id;
        this.post = new SimpleStringProperty(post);
        this.number = new SimpleIntegerProperty(number);
        this.lastName = new SimpleStringProperty(lastName);
        this.numberYellowCard = new SimpleIntegerProperty(0);
        this.numberRedCard = new SimpleIntegerProperty(0);
        this.numberBlueCard = new SimpleIntegerProperty(0);
        this.numberGoal = new SimpleIntegerProperty(0);
    }
    
    public Player(Player player){
        this.firstName = player.firstNameProperty();
        this.lastName = player.lastNameProperty();
        this.id = player.getId();
        this.numberLicence = (SimpleIntegerProperty) player.numberLicenceProperty();
        this.post = player.postProperty();
        this.lastClub = player.lastClubProperty();
        this.number = (SimpleIntegerProperty) player.numberProperty();
        this.numberGoal = (SimpleIntegerProperty) player.numberGoalProperty();
        this.numberSave = (SimpleIntegerProperty) player.numberSaveProperty();
        this.goalKeeper = player.isGoalKeeperProperty();
        this.pro = player.isProProperty();
        this.team = null;
    }

    public Player(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }
    
//    public IntegerProperty idProperty() {
//        return id;
//    }
    
    public int getNumberLicence(){
        return numberLicence.get();
    }
    
    public IntegerProperty numberLicenceProperty(){
        return numberLicence;
    }
    
    public IntegerProperty numberGoalProperty() {
        return numberGoal;
    }
    
    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public String getPost() {
        return post.get();
    }

    public void setPost(String post) {
        this.post.set(post);
    }

    public StringProperty postProperty() {
        return post;
    }
    
    public void setLastClub(String lastClub) {
        this.lastClub.set(lastClub);
    }
    
    public String getLastClub() {
        return lastClub.get();
    }

    public StringProperty lastClubProperty() {
        return lastClub;
    }
    
    public int getNumberGoal() {
        return numberGoal.get();
    }

    public void setNumberGoal(int numberGoal) {
        this.numberGoal.set(numberGoal);
    }

    public int getNumberYellowCard() { return numberYellowCard.get(); }

    public void setNumberYellowCard(int numberYellowCard) {
        this.numberYellowCard.set(numberYellowCard);
    }

    public IntegerProperty numberYellowCardProperty() { return numberYellowCard; }

    public void setNumberRedCard(int numberRedCard) {
        this.numberRedCard.set(numberRedCard);
    }

    public IntegerProperty numberRedCardProperty() { return numberYellowCard; }

    public void setNumberBlueCard(int numberBlueCard) {
        this.numberBlueCard.set(numberBlueCard);
    }

    public IntegerProperty numberBlueCardProperty() { return numberBlueCard; }

    public int getNumberSave() {
        return numberSave.get();
    }

    public void setNumberSave(int numberSave) {
        this.numberSave.set(numberSave);
    }

    public IntegerProperty numberSaveProperty() {
        return numberSave;
    }
    
    public boolean isGoalKeeper() {
        return goalKeeper.equals(goalKeeper);
    }
    
    public BooleanProperty isGoalKeeperProperty() {
        return goalKeeper;
    }
    
    public BooleanProperty isProProperty() {
        return pro;
    }
    
//    public void changeTeam(Team team){
//        StringProperty teamName = team.clubNameProperty();
//        this.team.findPlayer(this.getFirstName(), this.getLastName()).team.removePlayer(this);
//        //this.team = team;
//        team.addPlayer(this);
//        //this.addLastTeam(teamName);
//    }

}