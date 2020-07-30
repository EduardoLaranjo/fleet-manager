package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class TruckService {

    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    List<TruckDTO> getAllTrucks() {
        return this.truckRepository.findAll()
                .stream()
                .map(this::mapToTruckDTO)
                .collect(Collectors.toList());

    }

    public TruckDTO getTruck(String licensePlate) {
        return this.truckRepository.findByLicensePlate(licensePlate)
                .map(this::mapToTruckDTO)
                .orElseThrow(() -> new NotFoundException("The requested " + licensePlate + " resource does not exist"));
    }

    private TruckDTO mapToTruckDTO(Truck truck) {
        final var mappedPath = truck.getPath()
                .stream()
                .map(geoRecord -> new TruckDTO.Coordinate(geoRecord.getId(), geoRecord.getLat(), geoRecord.getLng()))
                .collect(Collectors.toList());

        return new TruckDTO(truck.getLicensePlate(), truck.getMonth(), truck.getYear(), mappedPath);
    }
}
