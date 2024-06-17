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

@Data
@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 40, nullable = false)
    private String tittle;
    @Column(length = 40, nullable = false)
    private String category;
    @Column(length = 450, nullable = false)
    private String content;

    @Column(length = 100000, nullable = false)
    private String image;
        
    @Column(length = 40 , nullable = false)
    private Integer likes;
    @Column(length = 40, nullable = false)
    private Integer disLikes;
   

    @OneToMany(mappedBy = "post")
    private List<Comment>comments = new ArrayList<>();

    


}




