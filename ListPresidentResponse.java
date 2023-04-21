package se.salt.jfs.restingapi.presidents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;


@JsonIgnoreProperties(ignoreUnknown = true)
public  record ListPresidentResponse(@JsonProperty("name") String name, @JsonProperty("uri") URI uri) {

    public ListPresidentResponse(String name,URI uri) {
        this.name=name;
        this.uri=uri;
    }
}
