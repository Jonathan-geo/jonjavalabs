package com.dragon.osodrac.repositories;


import com.dragon.osodrac.models.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, UUID> {

    boolean existsByCharacterName(String characterName);
    boolean existsByCharacterNumberAndCharacterBlock(String characterNumber, String characterBlock);
}