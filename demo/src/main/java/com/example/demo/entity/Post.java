package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
    @Column(unique = true)
     private String body;
     LocalDate createdAt;

    @ManyToOne
    ApplicationUser applicationUsers;

    public Post(String body, Date timeStamp, ApplicationUser newUser){}

    public Post(String body, LocalDate createdAt ,ApplicationUser applicationUsers) {
        this.body = body;
        this.createdAt = createdAt;
        this.applicationUsers = applicationUsers;
    }

    public ApplicationUser getApplicationUsers() {
        return applicationUsers;
    }

    public void setApplicationUsers(ApplicationUser applicationUsers) {
        this.applicationUsers = applicationUsers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
