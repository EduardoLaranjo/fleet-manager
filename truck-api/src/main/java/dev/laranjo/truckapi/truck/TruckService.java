package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
final class TruckService {

    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    List<Truck> getAllTrucks() {
        return this.truckRepository.findAll();
    }

    public Truck getTruck(long id) {
        return this.truckRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("truck with id " + id + " not found"));
    }
}
