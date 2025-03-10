package michal.malek.avsystemtask.util;

import michal.malek.avsystemtask.model.dto.CarDataResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarDataGeneratorIntegrationTest {

    @Test
    public void testExternalApiVehicleEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/vehicle/random_vehicle?size=1";

        ResponseEntity<List<CarDataResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarDataResponse>>() {}
        );

        List<CarDataResponse> carDataList = response.getBody();
        assertNotNull(carDataList);
        assertFalse(carDataList.isEmpty());

        CarDataResponse car = carDataList.get(0);
        assertNotNull(car.getLicense_plate());
        assertNotNull(car.getMake_and_model());
        System.out.println("license_plate=" + car.getLicense_plate()
                + ", make_and_model=" + car.getMake_and_model());
    }
}
