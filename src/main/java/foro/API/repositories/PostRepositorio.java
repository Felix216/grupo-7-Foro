package foro.API.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foro.API.models.Post;

@Repository
public interface PostRepositorio extends JpaRepository<Post,Long> {

}
