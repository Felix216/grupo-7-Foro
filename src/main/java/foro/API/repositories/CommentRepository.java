package foro.API.repositories;
import org.springframework.stereotype.Repository;

import foro.API.models.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

 
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
}
