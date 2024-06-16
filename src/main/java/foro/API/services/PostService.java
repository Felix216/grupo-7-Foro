package foro.API.services;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import foro.API.models.Post;
import foro.API.repositories.PostRepositorio;

@Service
public class PostService {
    @Autowired
    PostRepositorio postRepository;

    private final String storageLocation = "uploads/";

    public PostService() {
        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException e) {
            throw new RuntimeException("Could not create storage directory", e);
        }
    }

    // Obtener todas las publicaciones
    public List<Post> getAll() throws Exception {
        try {
            return postRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error fetching data: " + e.getMessage());
        }
    }

    // Crear Publicacion
     public Post createPost(String tittle, String category, String content, MultipartFile image, int likes, int dislikes) throws Exception {
        try {
            Post post = new Post();
            post.setTittle(tittle);
            post.setCategory(category);
            post.setContent(content);
            post.setLikes(likes);
            post.setDisLikes(dislikes);

            if (image != null && !image.isEmpty()) {
                String imageUrl = storeFile(image);
                post.setImage(imageUrl);
            }

            return postRepository.save(post);
        } catch (IOException e) {
            throw new Exception("Error storing image: " + e.getMessage());
        }
    }
        


    // Ordenar Por Popularidad
    public List<Post> getAllPopular() throws Exception {
        try {
            List<Post> allPosts = postRepository.findAll();
            return allPosts
                    .stream()
                    .sorted((p1, p2) -> Integer.compare(p2.getLikes(), p1.getLikes()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error fetching data: " + e.getMessage());
        }
    }


     // Guardar archivo
    private String storeFile(MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filepath = Paths.get(storageLocation, filename);
        Files.copy(file.getInputStream(), filepath);
        return filepath.toString();
    }
        
        
}
