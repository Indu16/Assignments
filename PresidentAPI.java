package se.salt.jfs.restingapi.presidents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;


@RestController()
@RequestMapping("/api/president")
@Validated
public class PresidentAPI {

    @Autowired
    PresidentRepository presidentRepository;
    @Autowired
    PresidentService service;

    private final static String BASE_PATH = "/api/president";

    @GetMapping(produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ListPresidentResponse>> getPresidents() {
        List<ListPresidentResponse> listPresidentResponses= service.getPresidents();
         return ResponseEntity
                 .ok()
                 .location(URI.create(BASE_PATH))
                 .body(listPresidentResponses);
    }
    @GetMapping(path="/{presidentId}",produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity <President> getById(@PathVariable@Valid @NotBlank String presidentId){
        if(this.checkInvalidUUID(presidentId))
            return ResponseEntity
                    .badRequest()
                    .build();
        President presidentById = service.getById(presidentId);
        if (presidentById == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity
                .ok()
                .location(URI.create(BASE_PATH+"/"+presidentId))
                .body(presidentById);
    }

    @PostMapping(produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<President> createPresident (@RequestBody@Valid President body){
        if(this.ValidateBadRequestBody(body))
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        String createStatus= service.createPresident(body);
        if(createStatus.equals("record already existing"))
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create(BASE_PATH+"/"+body.getId()))
                .body(body);
    }


    @PutMapping(path="/{presidentId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<President> updatePresident(@PathVariable String presidentId,@RequestBody President presidentNewData,HttpServletRequest request) {

        if(this.checkInvalidUUID(presidentId) || this.ValidateBadRequestBody(presidentNewData))
            return ResponseEntity
                    .badRequest()
                    .build();

        President updatedPresident = service.updatePresident(presidentId,presidentNewData);

        if(updatedPresident==null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .ok()
                .location(URI.create(request.getServletPath()))
                .body(updatedPresident);

    }

    @DeleteMapping(path="/{presidentId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<President> deletePresident(@PathVariable @Valid String presidentId){
        if(this.checkInvalidUUID(presidentId))
            return ResponseEntity
                    .badRequest()
                    .build();
        String deletedPresident=service.deletePresident(presidentId);
        return ResponseEntity
                .noContent()
                .build();
    }

    private boolean ValidateBadRequestBody(President body) {
        if (body.getName().isEmpty()
                || body.getId()==null
                || body.getFrom().length() != 4
                || !body.getFrom().matches("^[0-9]*$") )
            return true;

        if (!body.getTo().isEmpty()
                && (body.getTo().length() != 4
                || !body.getTo().matches("^[0-9]*$")))
            return true;

        return false;
    }

    private boolean checkInvalidUUID(String presidentId) {
        if(presidentId.length()==36) {
            try {
                UUID id = UUID.fromString(presidentId);
            }
            catch (IllegalArgumentException exception) {
                return true;
            }
            return false;
        }
        return true;
    }
}
