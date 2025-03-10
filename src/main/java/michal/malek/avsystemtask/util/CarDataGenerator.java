package michal.malek.avsystemtask.util;

import lombok.RequiredArgsConstructor;
import michal.malek.avsystemtask.model.dto.CarDataResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CarDataGenerator {

    private final RestTemplate restTemplate;
    private final List<CarDataResponse> carDataResponses = new LinkedList<>();

    public CarDataResponse getRandomCarData() {
        if(carDataResponses.isEmpty()){
            carDataResponses.addAll(getRandomCarDataIntoCache());
        }
        return carDataResponses.remove(0);
    }

    private List<CarDataResponse> getRandomCarDataIntoCache() {
        String API_URL = "https://random-data-api.com/api/vehicle/random_vehicle";
        String url = API_URL + "?size=" + 50;
        ResponseEntity<List<CarDataResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarDataResponse>>() {}
        );

        return response.getBody();
    }
}
