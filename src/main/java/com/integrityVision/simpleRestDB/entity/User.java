package com.integrityVision.simpleRestDB.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String firstName;
    private String lastName;
    @Column(name = "last_login_on")
    private Date lastLogOn;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastLogOn() {
        return lastLogOn;
    }

    public void setLastLogOn(Date lastLogOn) {
        this.lastLogOn = lastLogOn;
    }
}
