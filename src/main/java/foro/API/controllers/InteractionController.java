package foro.API.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
