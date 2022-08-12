package be.intecbrussel.schoolsout.data;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @Column(length = 20)
    private String login;
    private String passwordHash;
    private boolean active;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Person person;

    public User() {
    }


    public User(String login, String passwordHash, boolean active, Person person) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.active = active;
        this.person = person;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", active=" + active +
                ", person=" + person +
                '}';
    }
}
