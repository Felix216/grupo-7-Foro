package foro.API.controllers;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import foro.API.models.Comment;
import foro.API.services.CommentService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/posts/comments")
@CrossOrigin(origins = "http://localhost:8080") // Permite solicitudes desde tu frontend
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private CommentService commentService;

    
    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByPostId() throws Exception {
        List<Comment> comments = commentService.getAllCommets();
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) throws Exception {
        List<Comment> comments = commentService.getAllCommetsForPost(postId);
        return ResponseEntity.ok(comments);
    }

    
    @PostMapping
    public ResponseEntity<Comment> createCommentController(
    @RequestParam("content") String content,
    @RequestParam("userId") Long userId,
    @RequestParam("postId") Long postID) throws Exception {
        Comment comment = commentService.createComment(content,userId,postID);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }


     @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateCommentController(
            @PathVariable Long commentId,
            @RequestParam("content") String content) {
        try {
            Comment updatedComment = commentService.updateComment(commentId, content);
            return ResponseEntity.ok(updatedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteCommentController(
            @PathVariable Long commentId,
            @RequestParam("userId") Long userId) {
        try {
            commentService.deleteComment(commentId, userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
