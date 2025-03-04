package michal.malek.avsystemtask.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

//TODO REFACTOR
@SpringBootTest
public class LicensePlateGeneratorTest {

    @Autowired
    private LicensePlateGenerator licensePlateGenerator;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetRandomLicensePlatesReturnsValidPlates() {
        int size = 2;
        String expectedUrl = "https://random-data-api.com/api/vehicle/random_vehicle?size=" + size;
        String jsonResponse = "[{\"license_plate\":\"ABC123\"}, {\"license_plate\":\"XYZ789\"}]";

        mockServer.expect(ExpectedCount.once(), requestTo(expectedUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResponse, MediaType.APPLICATION_JSON));

        LinkedList<String> result = licensePlateGenerator.getRandomLicensePlates(size);
        result.forEach(System.out::println);
        mockServer.verify();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("ABC123"));
        assertTrue(result.contains("XYZ789"));
    }

    @Test
    public void testGetRandomLicensePlatesReturnsEmptyListWhenNoVehicles() {
        int size = 1;
        String expectedUrl = "https://random-data-api.com/api/vehicle/random_vehicle?size=" + size;
        String jsonResponse = "[]";

        mockServer.expect(ExpectedCount.once(), requestTo(expectedUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResponse, MediaType.APPLICATION_JSON));

        LinkedList<String> result = licensePlateGenerator.getRandomLicensePlates(size);
        result.forEach(System.out::println);
        mockServer.verify();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetRandomLicensePlatesSkipsNullLicensePlate() {
        int size = 2;
        String expectedUrl = "https://random-data-api.com/api/vehicle/random_vehicle?size=" + size;
        String jsonResponse = "[{\"license_plate\":null}, {\"license_plate\":\"DEF456\"}]";

        mockServer.expect(ExpectedCount.once(), requestTo(expectedUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResponse, MediaType.APPLICATION_JSON));

        LinkedList<String> result = licensePlateGenerator.getRandomLicensePlates(size);
        result.forEach(System.out::println);
        mockServer.verify();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains("DEF456"));
    }
}
