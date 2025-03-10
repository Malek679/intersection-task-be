package michal.malek.avsystemtask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import michal.malek.avsystemtask.model.dto.json.Command;
import michal.malek.avsystemtask.model.dto.json.JsonCommands;
import michal.malek.avsystemtask.model.dto.json.JsonResponse;
import michal.malek.avsystemtask.model.dto.json.StepStatus;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.vehicle.Car;
import michal.malek.avsystemtask.model.vehicle.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JsonCommandsService {
    private final IntersectionService intersectionService;

    public JsonResponse interpretCommands(MultipartFile json) throws IOException {
        intersectionService.clearIntersection();
        JsonCommands commands = this.getCommands(json);
        JsonResponse jsonResponse = new JsonResponse(new LinkedList<>());
        String stepCommand = "step";
        String addVehicleCommand = "addVehicle";
        String licensePlate = "JSON-CAR";

        for (Command command : commands.getCommands()){
            if(command.getType().equals(stepCommand)){
                intersectionService.changeTrafficLights();
                List<Vehicle> vehicles = intersectionService.nextStep();
                List<String> list = vehicles.stream().map(Vehicle::getName).toList();
                StepStatus stepStatus = new StepStatus(list);

                jsonResponse.getStepStatuses().add(stepStatus);
            }else if(command.getType().equals(addVehicleCommand)){
                String startRoad = command.getStartRoad();
                String endRoad = command.getEndRoad();
                String vehicleId = command.getVehicleId();
                Car car = new Car(vehicleId, 11, 10, licensePlate,
                        Direction.fromString(endRoad), Direction.fromString(startRoad));
                intersectionService.insertSingleCar(car);
            }
        }
        return jsonResponse;
    }

    public JsonCommands getCommands(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file.getInputStream(), JsonCommands.class);
    }
}
