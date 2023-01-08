package com.dragon.osodrac.services;

import com.dragon.osodrac.models.CharacterModel;
import com.dragon.osodrac.repositories.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CharacterService {

    final CharacterRepository characterRepository;
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    /*
     *  Post
     *      Service to sending post requisition.
     * */
    @Transactional
    public CharacterModel save(CharacterModel characterModel) {
        return characterRepository.save(characterModel);
    }

    public boolean existsCharacterNameSrv(String characterName) {
        return characterRepository.existsByCharacterName(characterName);
    }

    public boolean existsCharacterBlockSrv(String characterNumber, String characterBlock) {
        return characterRepository.existsByCharacterNumberAndCharacterBlock(characterNumber, characterBlock);
    }


    /*
     *   Get All
     *      Service to sending a get requisition of list type.
     *
     *     public List<CharacterModel> findAllCharacter() {
     *        return characterRepository.findAll();
     *     }
     * */
    public Page<CharacterModel> findAllCharacter(Pageable pageable) {
        return characterRepository.findAll(pageable);
    }

    /*
     *   Get by ID
     *      Service to sending get requisition list type.
     * */
    public Optional<CharacterModel> findCharacterById(UUID id) {
        return characterRepository.findById(id);
    }

    /*
     *   Delete by ID
     *      Service to sending delete requisition.
     * */
    @Transactional
    public void delete(CharacterModel characterModel) {
        characterRepository.delete(characterModel);
    }
}
