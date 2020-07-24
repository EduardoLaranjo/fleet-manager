package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@DisplayName("Truck Service Test")
class TruckServiceTest {

    @Test
    @DisplayName("get ALL trucks")
    void when_truck_then_get_all_trucks() {
        // given
        final var truckRepository = mock(TruckRepository.class);
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
                .licensePlate("98-MR-22")
                .path(List.of(GeoRecord.at(1.00, 1.00)))
                .build();

        when(truckRepository.findAll()).thenReturn(List.of(want0, want1));
        final var truckService = new TruckService(truckRepository);

        //when
        final List<TruckDTO> got = truckService.getAllTrucks();

        //then
        Assertions.assertEquals(2, got.size());

        Assertions.assertEquals("98-MR-21", got.get(0).getLicensePlate());
        Assertions.assertEquals(2020, got.get(0).getYear());
        Assertions.assertEquals(7, got.get(0).getMonth());

        Assertions.assertEquals("98-MR-22", got.get(1).getLicensePlate());
        Assertions.assertEquals(2020, got.get(1).getYear());
        Assertions.assertEquals(7, got.get(1).getMonth());

        verify(truckRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("get ONE truck by LICENSE")
    void when_truck_by_license_then_get_truck() {
        // given
        final var truckRepository = mock(TruckRepository.class);
        final var want = new Truck.Builder()
                .id(0L)
                .creationDate(LocalDateTime.of(2020, 7, 21, 18, 20))
                .month(7)
                .year(2020)
                .manufacturer("MAN")
                .licensePlate("98-MR-21")
                .path(List.of(GeoRecord.at(1.00, 1.00)))
                .build();

        when(truckRepository.findByLicensePlate("98-MR-21")).thenReturn(Optional.of(want));
        final var truckService = new TruckService(truckRepository);

        //when
        final TruckDTO got = truckService.getTruck("98-MR-21");

        //then
        Assertions.assertEquals(got.getMonth(), 7);
        Assertions.assertEquals(got.getYear(), 2020);
        Assertions.assertEquals(got.getLicensePlate(), "98-MR-21");

        final var pathGotten = got.getPath();

        Assertions.assertEquals(1.000, pathGotten.get(0).getLat());
        Assertions.assertEquals(1.000, pathGotten.get(0).getLog());

        verify(truckRepository, times(1)).findByLicensePlate("98-MR-21");

    }

    @Test
    @DisplayName("NOT FOUND truck")
    void when_truck_not_exist_then_not_found_exception() {
        // given
        final var truckRepository = mock(TruckRepository.class);
        when(truckRepository.findByLicensePlate(any())).thenReturn(Optional.empty());
        final var truckService = new TruckService(truckRepository);

        //when //then
        org.assertj.core.api.Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> truckService.getTruck("AA-AA-AA"))
                .withMessage("The requested AA-AA-AA resource does not exist");

        verify(truckRepository, times(1)).findByLicensePlate(any());
    }

}