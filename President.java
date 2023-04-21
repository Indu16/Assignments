package se.salt.jfs.restingapi.presidents;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public class President  {
    private String name;

    private UUID id;
    private String from;
    private String to;

    
    public President(String name, UUID id, String from, String to) {
        this.setName(name);
        this.setId(id);
        this.setFrom(from);
        this.setTo(to);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


}
