package foro.API.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foro.API.models.Interaction;


public interface InteractionRepository extends JpaRepository<Interaction,Long>{

    List<Interaction> findByPostId(Long id);
  
} 