package dev.salt.jfs.character_explorer;

import dev.salt.jfs.character_explorer.character_types.Character;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CharacterExplorer {
    private static final CharacterExplorer instance = new CharacterExplorer();
    //static {instance=new CharacterExplorer();}
    private final List<Character> charMaster = new ArrayList<>();
    public static CharacterExplorer explorer(){
        return instance;
    }
    public Integer nrOfCharacters() {
        return charMaster.size();
    }

    public void addCharacter(Character newChar) {
        charMaster.forEach(character -> {
                    if(character.getName().equals(newChar.getName())){
                        throw new IllegalArgumentException("Character name " + newChar.getName() + " already in list");
                    }

        });
        charMaster.add(newChar);
    }
}
