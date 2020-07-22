package dev.laranjo.truckapi.truck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

@DisplayName("Truck Service Test")
class TruckServiceTest {


    @Test
    @DisplayName("get ALL trucks")
    void when_get_trucks_then_get_list_of_all_trucks() {
        // given
        final var truckService = new TruckService();
        final var wantPositions = List.of(GeoRecord.at(1.00, 1.00));

        //when
        final List<Truck> got = truckService.getTrucks();

        //then
        Assertions.assertEquals(1, got.size());

        Assertions.assertEquals(0L, got.get(0).getId());
        Assertions.assertEquals("MAN", got.get(0).getManufacturer());
        Assertions.assertEquals(LocalDateTime.of(2020, 7, 21, 18, 20), got.get(0).getCreationDate());
        Assertions.assertEquals("98-MR-21", got.get(0).getLicensePlate());
        Assertions.assertEquals(2020, got.get(0).getYear());
        Assertions.assertEquals(7, got.get(0).getMonth());

        Assertions.assertEquals(wantPositions, got.get(0).getPath());
    }

    private void AssertTruck(Truck expected, Truck actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getManufacturer(), actual.getManufacturer());
        Assertions.assertEquals(expected.getCreationDate(), actual.getCreationDate());
        Assertions.assertEquals(expected.getLicensePlate(), actual.getLicensePlate());
        Assertions.assertEquals(expected.getYear(), actual.getYear());
        Assertions.assertEquals(expected.getMonth(), actual.getMonth());
        Assertions.assertEquals(expected.getPath(), actual.getPath());
    }

}