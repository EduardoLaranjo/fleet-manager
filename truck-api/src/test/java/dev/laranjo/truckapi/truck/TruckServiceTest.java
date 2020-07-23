package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DisplayName("Truck Service Test")
class TruckServiceTest {

    @Test
    @DisplayName("get ALL trucks")
    void when_get_trucks_then_get_list_of_all_trucks() {
        // given
        final var truckRepository = Mockito.mock(TruckRepository.class);
        final var want0 = new Truck.Builder()
                .id(0L)
                .creationDate(LocalDateTime.of(2020, 7, 21, 18, 20))
                .month(7)
                .year(2020)
                .manufacturer("MAN")
                .licensePlate("98-MR-21")
                .path(List.of(GeoRecord.at(1.00, 1.00)))
                .build();

        final var want1 = new Truck.Builder()
                .id(1L)
                .creationDate(LocalDateTime.of(2020, 7, 21, 18, 21))
                .month(7)
                .year(2020)
                .manufacturer("MAN")
                .licensePlate("98-MR-21")
                .path(List.of(GeoRecord.at(1.00, 1.00)))
                .build();

        Mockito.when(truckRepository.findAll()).thenReturn(List.of(want0, want1));
        final var truckService = new TruckService(truckRepository);

        //when
        final List<Truck> got = truckService.getAllTrucks();

        //then
        Assertions.assertEquals(2, got.size());
        Assertions.assertEquals(want0, got.get(0));
        Assertions.assertEquals(want1, got.get(1));

    }

    @Test
    @DisplayName("get truck by ID")
    void when_get_truck_by_id_then_get_the_truck() {
        // given
        final var truckRepository = Mockito.mock(TruckRepository.class);
        final var want = new Truck.Builder()
                .id(0L)
                .creationDate(LocalDateTime.of(2020, 7, 21, 18, 20))
                .month(7)
                .year(2020)
                .manufacturer("MAN")
                .licensePlate("98-MR-21")
                .path(List.of(GeoRecord.at(1.00, 1.00)))
                .build();

        Mockito.when(truckRepository.findById(Mockito.eq(0L))).thenReturn(Optional.of(want));
        final var truckService = new TruckService(truckRepository);

        //when
        final Truck got = truckService.getTruck(0);

        //then
        Assertions.assertEquals(got.getId(), 0L);
        Assertions.assertEquals(got.getCreationDate(), LocalDateTime.of(2020, 7, 21, 18, 20));
        Assertions.assertEquals(got.getMonth(), 7);
        Assertions.assertEquals(got.getYear(), 2020);
        Assertions.assertEquals(got.getManufacturer(), "MAN");
        Assertions.assertEquals(got.getLicensePlate(), "98-MR-21");
        Assertions.assertEquals(got.getPath(), List.of(GeoRecord.at(1.00, 1.00)));

    }

    @Test
    @DisplayName("get NOT FOUND truck")
    void when_get_truck_by_id_then_not_found() {
        // given
        final var truckRepository = Mockito.mock(TruckRepository.class);
        Mockito.when(truckRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        final var truckService = new TruckService(truckRepository);

        //when //then
        Assertions.assertThrows(NotFoundException.class,
                () -> truckService.getTruck(0),
                "truck with id 0 not found");
    }

}