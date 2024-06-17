package foro.API.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String rol;

    @Column(length = 50, nullable = false)
    private String password;


    
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
}
