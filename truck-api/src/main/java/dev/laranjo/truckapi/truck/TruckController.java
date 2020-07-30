package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.ApiError;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class TruckController {

    private final TruckService truckService;
    private final Logger logger;

    public TruckController(TruckService truckService, Logger logger) {
        this.truckService = truckService;
        this.logger = logger;
    }


    @GetMapping("/")
    List<TruckDTO> getAllTrucks() {
        logger.info("Looking for all trucks");
        return this.truckService.getAllTrucks();
    }

    @GetMapping(value = "/{licensePlate}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
    })
    TruckDTO getAllTrucks(@PathVariable String licensePlate) {
        logger.info("Looking for truck with license:  " + licensePlate);
        return this.truckService.getTruck(licensePlate);
    }
}
