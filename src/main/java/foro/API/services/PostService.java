package foro.API.services;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import foro.API.models.Post;
import foro.API.models.User;
import foro.API.repositories.PostRepositorio;

@Service
public class PostService {
    
    private final PostRepositorio postRepository;
    private final UserService userService;

    private final String storageLocation = "images/";

    public PostService(PostRepositorio postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;

        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException e) {
            throw new RuntimeException("Could not create storage directory", e);
        }
    }

    // Obtener todas las publicaciones
    public List<Post> getAll() throws Exception {
        try {

            List<Post> posts = postRepository.findAll();
            posts.forEach(post -> {
                post.getCommentsPostList().size(); // Cargar comentarios
                post.getInteractionsPostList().size(); // Cargar interacciones
            });
            return posts;

        } catch (Exception e) {
            throw new Exception("Error fetching data: " + e.getMessage());
        }
    }

    // Crear Publicacion
    public Post createPost(Long userId, String tittle, String category, String content, MultipartFile image)
            throws Exception {
        try {
            Post post = new Post();
            User user = userService.getUserByID(userId);
            post.setTittle(tittle);
            post.setCategory(category);
            post.setContent(content);
            post.setUser(user); // Asignar el usuario a la publicación

            if (image != null && !image.isEmpty()) {
                String imageUrl = storeFile(image);
                post.setImage(getPublicImageUrl(imageUrl)); // Guardar la URL pública de la imagen
            }
            Post savedPost = postRepository.save(post);

            return savedPost;
        } catch (IOException e) {
            throw new Exception("Error storing image: " + e.getMessage());
        }
    }

    // Ordenar Popularidad
    public List<Post> getPostsOrderByLikes() throws Exception {
        // Obtener todas las publicaciones
        try {
            List<Post> posts = postRepository.findAll();

            posts.sort((post1, post2) -> {
                int likesPost1 = countLikes(post1);
                int likesPost2 = countLikes(post2);
                // Orden descendente (mayor cantidad de likes primero)
                return Integer.compare(likesPost2, likesPost1);
            });

            return posts;
        } catch (Exception e) {
            throw new Exception("Error fetching data: " + e.getMessage());
        }
    }

    private int countLikes(Post post) {
        // Sumar la cantidad de likes (interacciones positivas) para el post dado

        return post.getInteractionsPostList().stream()
                .filter(interaction -> interaction.isTypeInteraction()) // Filtrar solo los likes
                .collect(Collectors.toList())
                .size();
    }

    // Obtener Post por ID
    public Post getPostByID(Long postId) throws Exception {
        return postRepository.findById(postId).orElseThrow(
                () -> new Exception("This post doesn't exist!"));
    }

    public List<Post> findAllByCategory(String category) throws Exception {
        try {
            return postRepository.findAllByCategory(category);
        } catch (Exception e) {
            throw new Exception("Error fetching data: " + e.getMessage());
        }

    }

    // Guardar archivo
    private String storeFile(MultipartFile file) throws Exception {
        try {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filepath = Paths.get(storageLocation, filename);
            Files.copy(file.getInputStream(), filepath);
            return filepath.toString();
        } catch (Exception e) {
            throw new Exception("No se pudo guardar el archivo");
        }
    }

    private String getPublicImageUrl(String filename) {
        // Construir la URL completa de la imagen pública
        return filename; 
    }
}