package com.dragon.osodrac.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CharacterDto {

    @NotBlank
    @Size(max = 70)
    private String characterNumber;

    @NotBlank
    @Size(max = 70)
    private String characterName;

    @NotNull
    private Double characterMagic;

    @NotNull
    private Double characterStrength;

    @NotNull
    private Double characterDefence;

    @NotBlank
    private String characterBlock;

    public String getCharacterNumber() {
        return characterNumber;
    }

    public void setCharacterNumber(String characterNumber) {
        this.characterNumber = characterNumber;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Double getCharacterMagic() {
        return characterMagic;
    }

    public void setCharacterMagic(Double characterMagic) {
        this.characterMagic = characterMagic;
    }

    public Double getCharacterStrength() {
        return characterStrength;
    }

    public void setCharacterStrength(Double characterStrength) {
        this.characterStrength = characterStrength;
    }

    public Double getCharacterDefence() {
        return characterDefence;
    }

    public void setCharacterDefence(Double characterDefence) {
        this.characterDefence = characterDefence;
    }

    public String getCharacterBlock() {
        return characterBlock;
    }

    public void setCharacterBlock(String characterBlock) {
        this.characterBlock = characterBlock;
    }
}



