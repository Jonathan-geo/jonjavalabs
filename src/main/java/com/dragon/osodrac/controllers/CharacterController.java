package com.dragon.osodrac.controllers;

import com.dragon.osodrac.dtos.CharacterDto;
import com.dragon.osodrac.models.CharacterModel;
import com.dragon.osodrac.services.CharacterService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/character") //("/dragon-character")
public class CharacterController {

    final CharacterService characterService;
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    /*
    * POST
    *   Sending post requisition with DTO model.
    *       http://localhost:8080/character
    * */
    @PostMapping
    public ResponseEntity<Object> saveCharacter(@RequestBody @Valid CharacterDto characterDto){
        if(characterService.existsCharacterNameSrv(characterDto.getCharacterName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("" +
                    "Conflict: Character Name is already in use!");
        }

        if(characterService.existsCharacterBlockSrv(characterDto.getCharacterNumber(), characterDto.getCharacterBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("" +
                    "Conflict: Number for this character is block!");
        }

        CharacterModel characterModel = new CharacterModel();
        BeanUtils.copyProperties(characterDto, characterModel);
        characterModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.save(characterModel));
    }


    //getMapping sem passar pelo dto, passando pelo dto, em forma de page e em forma de lista

    /*
     * GET
     *   Sending get requisition without DTO model.
     *      http://localhost:8080/character
     *
     *          @GetMapping
     *          public List<CharacterModel> getAllCharacter(){
     *              return characterService.findAllCharacter();
     *          }
     *
     *
     *          @GetMapping
     *          public ResponseEntity<List<CharacterModel>> getAllCharacter(){
     *              return ResponseEntity.status(HttpStatus.OK).body(characterService.findAllCharacter());
     *          }
     * */
    @GetMapping
    public ResponseEntity<Page<CharacterModel>> getAllCharacter(@PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findAllCharacter(pageable));
    }


    /*
     * GET
     *   Sending get requisition to return character by Id.
     *      http://localhost:8080/character/id
     * */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCharacterById(@PathVariable(value = "id") UUID id){
        Optional<CharacterModel> characterModelOptional = characterService.findCharacterById(id);

        if (characterModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(characterModelOptional.get());
    }

    /*
     * DELETE
     *   Sending get requisition to return character by Id.
     *      http://localhost:8080/character/id
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCharacterById(@PathVariable(value = "id") UUID id){
        Optional<CharacterModel> characterModelOptional = characterService.findCharacterById(id);

        if (characterModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found.");
        }

        characterService.delete(characterModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Character deleted successfully.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCharacterById(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid CharacterDto characterDto){
        Optional<CharacterModel> characterModelOptional = characterService.findCharacterById(id);
        if (characterModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found.");
        }

        /**
         *  Modelo que setamos todos os campos que queremos alterar
         */
        var characterModel = characterModelOptional.get();
        characterModel.setCharacterBlock(characterDto.getCharacterBlock());
        characterModel.setCharacterDefence(characterDto.getCharacterDefence());
        characterModel.setCharacterMagic(characterDto.getCharacterMagic());
        characterModel.setCharacterName(characterDto.getCharacterName());
        characterModel.setCharacterNumber(characterDto.getCharacterNumber());
        characterModel.setCharacterStrength(characterDto.getCharacterStrength());


        /**
         *  Modelo que setamos apenas os campos que não queremos alterar
         *
         *   var characterModel = new CharacterModel();
         *   BeanUtils.copyProperties(characterDto, characterModel);
         *   characterModel.setId(characterModelOptional.get().getId());
         *   characterModel.setRegistrationDate(characterModelOptional.get().getRegistrationDate());
        */

        return ResponseEntity.status(HttpStatus.OK).body(characterService.save(characterModel));
    }

}
