package sample.model;

import java.time.LocalDate;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jdk.nashorn.internal.runtime.PropertyAccess;

public class Person extends RecursiveTreeObject<Person>  {
    protected StringProperty firstName;
    protected StringProperty lastName;
    protected StringProperty gender;
    protected IntegerProperty age;
    protected IntegerProperty height;
    protected IntegerProperty weight;
    protected ObjectProperty<LocalDate> birthday;
    protected StringProperty nationality;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        // Some initial dummy data, just for convenient testing.
        this.nationality = new SimpleStringProperty("some nationality");
        this.gender = new SimpleStringProperty("homme");
        this.age = new SimpleIntegerProperty(12);
        this.height = new SimpleIntegerProperty(192);
        this.weight = new SimpleIntegerProperty(89);
        this.birthday = new SimpleObjectProperty<>(LocalDate.of(1999, 2, 21));
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(age);
    }

    public Person(String lastName) {
        this.lastName = new SimpleStringProperty("");
    }

    public Person(Person person){
        this.firstName = person.firstNameProperty();
        this.lastName = person.lastNameProperty();  
        this.gender = person.genderProperty();
        this.age = person.ageProperty();
        this.height = person.heightProperty();
        this.weight = person.weightProperty();
        this.birthday = person.birthdayProperty();
        this.nationality = person.nationalityProperty();
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

    public String getNationality() {
        return nationality.get();
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }
    
    public String getGender() {
        return gender.get();
    }
    
    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public int getHeight() {
        return height.get();
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public int getWeight() {
        return weight.get();
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
}