package foro.API.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foro.API.models.Interaction;
import foro.API.models.Post;
import foro.API.models.User;
import foro.API.repositories.InteractionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InteractionService {

    @Autowired
    private InteractionRepository interactionRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    public Interaction createLikeInteraction(Long userId, Long postId) throws Exception {
        Post post = postService.getPostByID(postId);
        User user = userService.getUserByID(userId);

        // Verificar si el usuario ya ha interactuado con esta publicación
        if (hasUserInteractedWithPost(user, post)) {
            throw new IllegalArgumentException("User already interacted with post: " + postId);
        }
        Interaction interaction = new Interaction(user, post, true);// se crea el like
        interactionRepository.save(interaction);
        
        // Asociar la interacción a la publicación y al usuario
        post.getInteractionsPostList().add(interaction);
        user.getInteractionsListUser().add(interaction);

        return interaction;
    }

    public Interaction createDislikeInteraction(Long userId, Long postId) throws Exception {
        Post post = postService.getPostByID(postId);
        User user = userService.getUserByID(userId);

        // Verificar si el usuario ya ha interactuado con esta publicación
        if (hasUserInteractedWithPost(user, post)) {
            throw new IllegalArgumentException("User already interacted with post: " + postId);
        }

        Interaction interaction = new Interaction(user, post, false); // Crear un dislike
        interactionRepository.save(interaction);

        // Asociar la interacción a la publicación y al usuario
        post.getInteractionsPostList().add(interaction);
        user.getInteractionsListUser().add(interaction);

        return interaction;
    }

    private boolean hasUserInteractedWithPost(User user, Post post) {
        return user.getInteractionsListUser().stream()
                .anyMatch(interaction -> interaction.getPost().equals(post));
    }
}
