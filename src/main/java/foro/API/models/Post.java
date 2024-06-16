package foro.API.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 40)
    private String tittle;
    @Column(length = 40)
    private String category;
    @Column(length = 450)
    private String content;

    @Column(length = 100000)
    private String image;
        
    @Column(length = 40)
    private Integer likes;
    @Column(length = 40)
    private Integer disLikes;
    // me falta agregar el atributo comentario

    /*
     TAMBIEN LA ID DEL USUARIO.
     */
    
}




