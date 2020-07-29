package dev.laranjo.truckapi.truck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/truck")
class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping("/")
    List<TruckDTO> getAllTrucks() {
        return this.truckService.getAllTrucks();
    }

    @GetMapping(value = "/{licensePlate}")
    TruckDTO getAllTrucks(@PathVariable String licensePlate) {
        return this.truckService.getTruck(licensePlate);
    }
}
