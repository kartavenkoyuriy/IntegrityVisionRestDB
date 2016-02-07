package com.integrityVision.simpleRestDB.entity;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "last_login_on", nullable = false)
    private Date lastLogOn;

    public User() {
    }

    public User(String login, String firstName, String lastName, Date lastLogOn) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLogOn = lastLogOn;
    }

    public User(Integer id, String login, String firstName, String lastName, Date lastLogOn) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLogOn = lastLogOn;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getLastLogOn() {
        return lastLogOn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLastLogOn(Date lastLogOn) {
        this.lastLogOn = lastLogOn;
    }

    public String toJsonStringWithId(){
        String dateToString = new SimpleDateFormat("yyyy-MM-dd").format(lastLogOn);
        return "{" + "\"id\":\"" + id
                + "\",\"login\":\"" + login
                + "\",\"firstName\":\"" + firstName
                + "\",\"lastName\":\"" + lastName
                + "\",\"lastLogOn\":\"" + dateToString + "\"}";
    }

    public String toJsonStringWithoutId(){
        String dateToString = new SimpleDateFormat("yyyy-MM-dd").format(lastLogOn);
        return "{\"login\":\"" + login
                + "\",\"firstName\":\"" + firstName
                + "\",\"lastName\":\"" + lastName
                + "\",\"lastLogOn\":\"" + dateToString + "\"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, user.id)
                .append(login, user.login)
                .append(firstName, user.firstName)
                .append(lastName, user.lastName)
                .append(lastLogOn, user.lastLogOn)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .append(login)
                .append(firstName)
                .append(lastName)
                .append(lastLogOn)
                .toHashCode();
    }
}