package foro.API.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import foro.API.models.Interaction;


public interface InteractionRepository extends JpaRepository<Interaction,Long>{

    
} 