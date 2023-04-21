package se.salt.jfs.restingapi.presidents;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
public class PresidentRepository {
    private Map<UUID, President> presidents;

    public PresidentRepository(){
        presidents = new HashMap<>();
        presidents.put(UUID.fromString("2f81a686-7531-11e8-86e5-f0d5bf731f68"), new President("George W. Bush", UUID.fromString("2f81a686-7531-11e8-86e5-f0d5bf731f68"), "2001", "2009"));
        presidents.put( UUID.fromString("f9ce325d-ed8c-4fad-899b-fc997ed199ad"), new President("Barack Obama", UUID.fromString("f9ce325d-ed8c-4fad-899b-fc997ed199ad"), "2009", "2017"));
        presidents.put(UUID.fromString("b769d25a-86dc-4ec6-a022-dfa4112354f9"), new President("Donald J. Trump", UUID.fromString("b769d25a-86dc-4ec6-a022-dfa4112354f9"), "2017", "2021"));
        presidents.put(UUID.fromString("822dcf18-54eb-4394-8884-1c73addf25c7"), new President("Joe Biden", UUID.fromString("822dcf18-54eb-4394-8884-1c73addf25c7"), "2021", null));
    }


    public Collection<President> getPresidents(){
        return presidents.values();
    }


    public President getById(String presidentId){
        return presidents.get(UUID.fromString(presidentId));
    }

    public void create(President president) {
        if(presidents.get(president.getId()) != null) {
            throw new UnsupportedOperationException();
        }
        presidents.put(president.getId(), president);
    }
    
    public President updatePresident(President president) {
        if(presidents.get(president.getId()) == null) {
            throw new UnsupportedOperationException();
        }
        presidents.put(president.getId(), president);
        return president;
    }

    public President deletePresident(String id){
        return presidents.remove(UUID.fromString(id));
    }

    }


