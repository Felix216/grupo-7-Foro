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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/posts/comments")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private CommentService commentService;

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
    
}
