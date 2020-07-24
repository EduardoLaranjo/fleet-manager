package dev.laranjo.truckapi.truck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping("/truck")
    List<TruckDTO> getAllTrucks() {
        return this
                .truckService.getAllTrucks();
    }

    @GetMapping(value = "/truck/{licensePlate}")
    TruckDTO getAllTrucks(@PathVariable String licensePlate) {
        return this.truckService.getTruck(licensePlate);
    }
}
