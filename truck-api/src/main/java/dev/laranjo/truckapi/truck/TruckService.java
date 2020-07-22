package dev.laranjo.truckapi.truck;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
final class TruckService {



    List<Truck> getTrucks() {
        final var truck = new Truck();
        truck.setId(0L);
        truck.setManufacturer("MAN");
        truck.setCreationDate(LocalDateTime.of(2020, 7, 21, 18, 20));
        truck.setLicensePlate("98-MR-21");
        truck.setMonth(7);
        truck.setYear(2020);
        truck.setPath(List.of(GeoRecord.at(1.00, 1.00)));
        return List.of(truck);
    }

}
