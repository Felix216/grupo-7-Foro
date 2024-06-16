package foro.API.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import foro.API.services.PostService;
import foro.API.models.Post;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

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
            return ResponseEntity.ok(postService.getAllPopular());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPostController(@RequestParam("tittle") String tittle,
            @RequestParam("category") String category, @RequestParam("content") String content,
            @RequestParam("image") MultipartFile image, @RequestParam("likes") int likes,
            @RequestParam("dislikes") int disLikes) throws Exception {
        try {
            Post createdPost = postService.createPost(tittle, category, content, image,
                    likes, disLikes);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
