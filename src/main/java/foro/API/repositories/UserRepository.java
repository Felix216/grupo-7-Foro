package foro.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foro.API.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
