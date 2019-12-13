package sample.model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class ChampionshipDay {
    
    protected IntegerProperty number;
    protected ObjectProperty<LocalDate> date;
    protected ListProperty<Match> matchs;
    
    public ChampionshipDay(int number, LocalDate date, Match matchs) {
        this.number = new SimpleIntegerProperty(2);
        this.date = new SimpleObjectProperty<>(LocalDate.of(2019, 11, 26));
        this.matchs = new SimpleListProperty<>(null);
    }
    
    public ChampionshipDay(ChampionshipDay championShip) {
        this.number = championShip.numberProperty();
        this.date = championShip.dateProperty();
        this.matchs = championShip.matchsProperty();
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

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
    
    public ObservableList<Match> getMatchs() {
        return matchs.get();
    }
    
    public ListProperty<Match> matchsProperty() {
	return matchs;
    }
    
    public void setMatchs(ObservableList<Match> value) {
        matchs.set(value);
    }
    
    public void addMatch(Match math){
        this.matchs.add(math);
    }
}
