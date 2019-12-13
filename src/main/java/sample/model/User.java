package sample.model;

public class User extends Person {

    private Integer id;
    private String email;
    private String dob;
    private String password;
    private String username;
    
    public User() {
    }
    
    public User(String firstName, String lastName) {
        super(firstName, lastName);
    }
    
    public User(String firstName, String lastName, Integer id, String username, String email, String password) {
        super(firstName, lastName);
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}