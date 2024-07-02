package foro.API.services;


import java.util.List;
import java.util.Optional;

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

    
    public List<Comment> getAllCommets()throws Exception{
        try{
            return commentRepository.findAll();
        }catch(Exception e){
            throw new Exception("Error fetching data: " + e.getMessage());
        }
    }


    public Comment updateComment(Long commentId, String content) throws Exception {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setContent(content);
            return commentRepository.save(comment);
        } else {
            throw new Exception("Comment not found");
        }
    }

    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            if (Long.valueOf(comment.getUser().getId()).equals(userId)) {
                commentRepository.deleteById(commentId);
            } else {
                throw new Exception("You are not authorized to delete this comment");
            }
        } else {
            throw new Exception("Comment not found");
        }
    }

}
