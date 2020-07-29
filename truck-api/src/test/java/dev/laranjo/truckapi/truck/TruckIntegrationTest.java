package dev.laranjo.truckapi.truck;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Truck Integration Test")
class TruckIntegrationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TruckRepository truckRepository;

    private static boolean isSetUp = false;

    @BeforeEach
    void setUp() {
        if (!isSetUp) {
            this.truckRepository.saveAndFlush(new Truck("11-AA-11", "MAN", 2020, 7, List.of()));
            this.truckRepository.saveAndFlush(new Truck("11-BB-11", "MAN", 2020, 7, List.of()));
        }
        isSetUp = true;
    }

    @Test
    @DisplayName("get ONE truck by LICENSE")
    void when_get_truck_by_license_it_should_get_it() throws JSONException {
        final var restTemplate = new RestTemplate();
        final var got = restTemplate.getForObject("http://localhost:{0}/api/truck/AA-11-AA", String.class, randomServerPort);
        final var want = "{" +
                "   \"licensePlate\": \"11-AA-11\"," +
                "   \"year\": 2020," +
                "   \"month\": 7," +
                "   \"path\": []" +
                "}";

        JSONAssert.assertEquals(want, got, JSONCompareMode.LENIENT);
    }

    @Test
    @DisplayName("get ALL trucks")
    void when_get_trucks_by_id_it_should_get_it() throws JSONException {
        final var restTemplate = new RestTemplate();
        final var got = restTemplate.getForObject("http://localhost:{0}/api/truck/", String.class, randomServerPort);
        final var want = "[{" +
                "   \"licensePlate\": \"11-AA-11\"," +
                "   \"year\": 2020," +
                "   \"month\": 7," +
                "   \"path\": []" +
                "}, {" +
                "   \"licensePlate\": \"11-BB-11\"," +
                "   \"year\": 2020," +
                "   \"month\": 7," +
                "   \"path\": []" +
                "}]";

        JSONAssert.assertEquals(want, got, JSONCompareMode.LENIENT);
    }

    @Test
    @DisplayName("NOT FOUND truck")
    void when_truck_by_license_not_found_then_should_error() throws JSONException {
        final var restTemplate = new RestTemplate();
        final var want = "{" +
                "  \"status\": 404," +
                "  \"error\": \"Not Found\"," +
                "  \"message\": \"The requested AA-AA-AA resource does not exist\"" +
                "}";

        try {
            restTemplate.getForEntity("http://localhost:{0}/api/truck/AA-AA-AA", String.class, randomServerPort);
        } catch (HttpClientErrorException e) {
            JSONAssert.assertEquals(want, e.getResponseBodyAsString(), JSONCompareMode.LENIENT);
        }
    }

}
