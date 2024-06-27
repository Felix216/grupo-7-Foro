package foro.API.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import foro.API.models.Post;
import foro.API.services.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private final PostService postService;

    @GetMapping()
    public ResponseEntity<?> getAllPost() throws Exception {
        try {
            return ResponseEntity.ok(postService.getAll());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/popular")
    public ResponseEntity<?> getAllPostForPopularidad() throws Exception {
        try {
            return ResponseEntity.ok(postService.getPostsOrderByLikes());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPostController(
            @RequestParam("userId") Long userId,
            @RequestParam("tittle") String tittle,
            @RequestParam("category") String category,
            @RequestParam("content") String content,
            @RequestParam("image") MultipartFile image) throws Exception {
        try {
            Post createdPost = postService.createPost(userId,tittle, category, content, image);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

