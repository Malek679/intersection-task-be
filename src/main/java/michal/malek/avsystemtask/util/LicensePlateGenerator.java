package michal.malek.avsystemtask.util;

import lombok.RequiredArgsConstructor;
import michal.malek.avsystemtask.model.LicensePlateResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LicensePlateGenerator {

    private final RestTemplate restTemplate;
    private final String API_URL = "https://random-data-api.com/api/vehicle/random_vehicle";

    public LinkedList<String> getRandomLicensePlates(int size) {
        String url = API_URL + "?size=" + size;
        ResponseEntity<List<LicensePlateResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LicensePlateResponse>>() {}
        );
        List<LicensePlateResponse> vehicles = response.getBody();
        LinkedList<String> plates = new LinkedList<>();

        if (vehicles != null && !vehicles.isEmpty()) {
            for (LicensePlateResponse vehicle : vehicles) {
                if (vehicle.getLicense_plate() != null) {
                    plates.add(vehicle.getLicense_plate());
                }
            }
        }
        return plates;
    }
}
