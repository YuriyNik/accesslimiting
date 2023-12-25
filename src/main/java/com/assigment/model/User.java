package com.assigment.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime lastLoginTimeUtc;
    public User(){
    }
    public User(String firstName, String lastName,LocalDateTime lastLoginTimeUtc ){
    this.firstName=firstName;
    this.lastName=lastName;
    this.lastLoginTimeUtc=lastLoginTimeUtc;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastLoginTimeUtc(LocalDateTime lastLoginTimeUtc) {
        this.lastLoginTimeUtc = lastLoginTimeUtc;
    }

    public LocalDateTime getLastLoginTimeUtc() {
        return lastLoginTimeUtc;
    }

    @Override
    public String toString() {
        return "{id="+this.id+";=firstName"+this.firstName+";=lastName;"+this.lastName+";lastLoginTimeUtc="+this.lastLoginTimeUtc+"}";
    }
}
