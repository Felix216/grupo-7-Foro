package foro.API.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import foro.API.models.Interaction;
import foro.API.services.InteractionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/interactions")
@RequiredArgsConstructor
public class InteractionController {
 
    @Autowired
    private InteractionService interactionService;

    @GetMapping("/{postId}")
    public ResponseEntity<List<Interaction>> getInteractionForPost(@PathVariable Long postId) throws Exception {
        List<Interaction> comments = interactionService.getAllInteractionsForPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/like")
    public ResponseEntity<Interaction> likePost(
            @RequestParam("userId") Long userId,
            @RequestParam("postId") Long postId) throws Exception {

        Interaction interaction = interactionService.createLikeInteraction(userId, postId);
        return ResponseEntity.ok(interaction);
    }

    @PostMapping("/dislike")
    public ResponseEntity<Interaction> dislikePost(
            @RequestParam("userId") Long userId,
            @RequestParam("postId") Long postId) throws Exception {

        Interaction interaction = interactionService.createDislikeInteraction(userId, postId);
        return ResponseEntity.ok(interaction);
    }
}
