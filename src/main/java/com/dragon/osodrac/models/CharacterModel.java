package com.dragon.osodrac.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_CHARACTER")
public class CharacterModel implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String characterNumber;

    @Column(nullable = false, length = 70)
    private String characterName;

    @Column(nullable = false)
    private Double characterMagic;

    @Column(nullable = false)
    private Double characterStrength;

    @Column(nullable = false)
    private Double characterDefence;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false, length = 30)
    private String characterBlock;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCharacterBlock() {
        return characterBlock;
    }

    public void setCharacterBlock(String characterBlock) {
        this.characterBlock = characterBlock;
    }
}