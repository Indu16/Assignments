package dev.salt.jfs.character_explorer;


import dev.salt.jfs.character_explorer.character_classes.Mage;
import dev.salt.jfs.character_explorer.character_classes.Ranger;
import dev.salt.jfs.character_explorer.character_classes.Warrior;
import dev.salt.jfs.character_explorer.character_types.Character;
import dev.salt.jfs.character_explorer.character_types.Dwarf;
import dev.salt.jfs.character_explorer.character_types.Elf;
import dev.salt.jfs.character_explorer.character_types.Human;
import dev.salt.jfs.character_explorer.character_types.abilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Component
@Scope("prototype")
public class CharacterBuilder {
    public Character character;
    @Autowired
    private CharacterExplorer explorer1 = CharacterExplorer.explorer();

    public CharacterBuilder() {
    }

    public Character build() {
        explorer1.addCharacter(character);
        return character;
    }



    public CharacterBuilder createElf(String charName) {
        try {
            if (!character.getTypeName().isEmpty())
                throw new IllegalArgumentException("Character Type cannot be reset!");
        }
        catch (NullPointerException e) {
            character = new Elf("Elf", new ElfSpecialMove());
            character.setName(charName);
        }
        return this;
    }

    public CharacterBuilder createDwarf(String charName) {
        try {
            if (!character.getTypeName().isEmpty())
                throw new IllegalArgumentException("Character Type cannot be reset!");
            }
        catch (NullPointerException e) {
            character = new Dwarf("Dwarf", new DwarfSpecialMove());
            character.setName(charName);
            }
        return this;
    }

    public CharacterBuilder createHuman(String charName) {
        try {
            if (!character.getTypeName().isEmpty())
                throw new IllegalArgumentException("Character Type cannot be reset!");
            }
        catch (NullPointerException e) {
            character = new Human("Human", new HumanSpecialMove());
            character.setName(charName);
            }
        return this;
    }

    public CharacterBuilder makeMage() {
        try {
            if (!character.getCharacterClass().getClassName().isEmpty())
                throw new IllegalArgumentException("Character Class cannot be reset!");
            }
        catch (NullPointerException e) {
            character.setCharacterClass(new Mage());
            }
        return this;
    }

    public CharacterBuilder makeRanger() {
        try {
            if (!character.getCharacterClass().getClassName().isEmpty())
                throw new IllegalArgumentException("Character Class cannot be reset!");
            }
        catch (NullPointerException e) {
            character.setCharacterClass(new Ranger());
        }
        return this;
    }

    public CharacterBuilder makeWarrior() {
        try {
            if (!character.getCharacterClass().getClassName().isEmpty())
                throw new IllegalArgumentException("Character Class cannot be reset!");
            }
        catch (NullPointerException e) {
            character.setCharacterClass(new Warrior());
            }
        return this;
    }

    public CharacterBuilder addAbility(CharacterAbility newCharAbility){
        if(newCharAbility.name().equals( "Shadow Walk")|| newCharAbility.name().equals("Believe")||
        newCharAbility.name().equals("Diamond skin"))
            throw new IllegalArgumentException("Can't set special move!");
        Arrays.stream(character.getAbilities()).toList()
                .forEach(characterAbility -> {
                    if(characterAbility.name().equals(newCharAbility.name())){
                        throw new IllegalArgumentException("Ability already added");
                    }
                });
        character.setAbilities(newCharAbility);
        return this;
    }
}

