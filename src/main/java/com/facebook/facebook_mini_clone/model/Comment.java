package com.facebook.facebook_mini_clone.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Table(name = ("comments"))
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;

    @NotBlank(message = "Comment body is required")
    private String commentText;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private Post post;

    @OneToMany
    private Set<Like> likes;

    @ManyToOne
    private User user;
}
