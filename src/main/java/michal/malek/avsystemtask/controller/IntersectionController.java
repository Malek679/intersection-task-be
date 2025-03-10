package michal.malek.avsystemtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import michal.malek.avsystemtask.model.dto.IntersectionResponse;
import michal.malek.avsystemtask.model.dto.json.Command;
import michal.malek.avsystemtask.model.dto.json.JsonResponse;
import michal.malek.avsystemtask.model.log.LogEntity;
import michal.malek.avsystemtask.model.vehicle.Vehicle;
import michal.malek.avsystemtask.service.IntersectionService;
import michal.malek.avsystemtask.service.JsonCommandsService;
import michal.malek.avsystemtask.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntersectionController {
    private final IntersectionService intersectionService;
    private final JsonCommandsService jsonCommandsService;
    private final LogService logService;

    @GetMapping("/get-intersection")
    public ResponseEntity<IntersectionResponse> getIntersection() {
        return ResponseEntity.ok(intersectionService.getIntersection());
    }

    @GetMapping("/generate-cars")
    public ResponseEntity<?> generateCars() {
        intersectionService.fillRoutesRandomBound3();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/next-step")
    public ResponseEntity<List<Vehicle>> nextStep() {
        List<Vehicle> vehicles = intersectionService.nextStep();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/set-intersection")
    public ResponseEntity<?> setIntersection(@RequestParam String intersectionName) {
        try{
            intersectionService.setDifferentIntersection(intersectionName);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-logs")
    public ResponseEntity<List<LogEntity>> getLogs() {
        List<LogEntity> all = logService.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping(
            value = "/upload-json",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JsonResponse> uploadJsonFile(@RequestParam("file") MultipartFile file) {
        try{
            JsonResponse result = jsonCommandsService.interpretCommands(file);
            return ResponseEntity.ok(result);
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
