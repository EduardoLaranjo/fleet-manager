package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TruckController.class)
@DisplayName("Truck Controller Tests")
class TruckControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TruckService truckService;

    @Test
    @DisplayName("get ALL trucks")
    void when_truck_then_get_all_trucks() throws Exception {
        //given
        final var truck1 = new TruckDTO("AA-AA-AA", 7, 2020, List.of());
        final var truck2 = new TruckDTO("AA-BB-AA", 7, 2020, List.of());

        when(truckService.getAllTrucks()).thenReturn(List.of(truck1, truck2));

        //when
        mvc.perform(MockMvcRequestBuilders.get("/api/truck/"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        "[{" +
                                "    \"licensePlate\": \"AA-AA-AA\"," +
                                "    \"year\": 2020," +
                                "    \"month\": 7," +
                                "    \"path\": []" +
                                "},{" +
                                "    \"licensePlate\": \"AA-BB-AA\"," +
                                "    \"year\": 2020," +
                                "    \"month\": 7," +
                                "    \"path\": []" +
                                "}]", true));
    }

    @Test
    @DisplayName("get ONE truck by LICENSE")
    void when_truck_by_license_plate_then_get_truck() throws Exception {
        //given
        final var truck = new TruckDTO("AA-AA-AA", 7, 2020, List.of());

        when(truckService.getTruck(eq("AA-AA-AA"))).thenReturn(truck);

        //when
        mvc.perform(MockMvcRequestBuilders.get("/api/truck/AA-AA-AA"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{" +
                        "    \"licensePlate\": \"AA-AA-AA\"," +
                        "    \"year\": 2020," +
                        "    \"month\": 7," +
                        "    \"path\": []" +
                        "}", true));
    }

    @Test
    @DisplayName("NOT FOUND truck")
    void when_not_exist_truck_then_error_404() throws Exception {
        //given
        when(truckService.getTruck(eq("AA-AA-AA")))
                .thenThrow(new NotFoundException("The requested AA-AA-AA resource does not exist"));

        //when
        mvc.perform(MockMvcRequestBuilders.get("/api/truck/AA-AA-AA"))
                //then
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{" +
                        "  \"status\": 404," +
                        "  \"error\": \"Not Found\"," +
                        "  \"message\": \"The requested AA-AA-AA resource does not exist\"" +
                        "}"));
    }

}