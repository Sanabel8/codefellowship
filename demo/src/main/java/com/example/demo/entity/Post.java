package com.example.demo.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
    @Column(unique = true)
     private String body;
     private String username;
     @CreationTimestamp
     private Date createdAt;

    @ManyToOne
    private ApplicationUser applicationUsers;

    public Post(){}

    public Post(String body,ApplicationUser applicationUsers) {
        this.body = body;
        this.applicationUsers = applicationUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
