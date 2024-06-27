package foro.API.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foro.API.models.Comment;
import foro.API.models.Post;
import foro.API.models.User;
import foro.API.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    

    //crear comentario
    public Comment createComment(String content,Long userId, Long postId) throws Exception {
        Comment comment = new Comment();
        comment.setContent(content);
        
        User user = userService.getUserByID(userId);

        System.out.println("nombre del usuario: "+user.getUsername());
        Post post = postService.getPostByID(postId);
        comment.setUser(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    // mostrar los mensajes de la publicacion (id)
    public List<Comment> getAllCommetsForPost(Long postId)throws Exception{
        
        try{
            return commentRepository.findByPostId(postId);
        }catch(Exception e){
            throw new Exception("Error fetching data: " + e.getMessage());
        }
    }

}
