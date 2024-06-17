package foro.API.models;

 
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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String content;



    /*
     * Cada comentario tendra una id_publicacion en donde estara el comentario 
     * y un id_usuario que sera el que crea el comentario 
     */

     //Relacion con Usuario,  1 usuario puede tener muchos comentarios

     @ManyToOne
     @JoinColumn(name = "user_id",nullable = false)
     private User user;

     //Relacion con publicaciones, 1 publicacion puede tener muchos comentarios
     // lo mismo que decir = "Muchos comentarios estan en 1 publicacion"
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;




}
