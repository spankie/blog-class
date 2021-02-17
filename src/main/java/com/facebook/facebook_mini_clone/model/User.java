package com.facebook.facebook_mini_clone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name = ("users"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String firstName;
    private String lastName;
    @Column(unique = true) //two users cannot have the same email
    private String email;
    private String password;
    private String gender;
    private Date birthDay;

    @OneToMany
    private Set<Post> posts;

    @JsonIgnore
    @OneToMany
    private Set<Comment> comments;

    @JsonIgnore
    @OneToMany
    private Set<Like> likes;

}
