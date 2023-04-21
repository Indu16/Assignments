package se.salt.jfs.restingapi.presidents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PresidentService{
    private final PresidentRepository repo;
    private final static String BASE_PATH = "/api/president";

    public PresidentService(@Autowired PresidentRepository repo){
        this.repo=repo;
    }

    public List<ListPresidentResponse> getPresidents(){
        return repo.getPresidents().stream()
                .map(i->new ListPresidentResponse(i.getName(), URI.create(BASE_PATH+"/"+i.getId())))
                .collect(Collectors.toList());
    }

    public President getById(String presidentId) {
        return repo.getById(presidentId);
    }

    public String createPresident(President body){
        President checkPresident = repo.getById(String.valueOf(body.getId()));
        if(checkPresident!=null)
            return "record already existing";
        repo.create(body);
        return "record created";
    }

    public President updatePresident(String presidentId, President newPresidentData) {
        if(repo.getById(presidentId)==null)
            return null;
        repo.updatePresident(newPresidentData);
        return repo.getById(String.valueOf(newPresidentData.getId()));

    }

    public String deletePresident(String presidentId) {
        String deletedPresident;
        if(repo.getById(presidentId)!=null)
            deletedPresident=repo.getById(presidentId).getName();
        else
            deletedPresident="No record";
        repo.deletePresident(presidentId);
        return deletedPresident;
    }

}
