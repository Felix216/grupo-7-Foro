package foro.API.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "interaction")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Interaction {
    
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean typeInteraction;

    


    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private User user;


    
    public Interaction(){}

    //constructor con parametros 
    public Interaction(User user2, Post post2, boolean b) {
        this.user= user2;
        this.post = post2;
        this.typeInteraction = b;
    }

}
