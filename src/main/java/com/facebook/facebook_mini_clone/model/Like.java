package com.facebook.facebook_mini_clone.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = ("likes"))
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long likeId;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private Post post;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private User user;
}
