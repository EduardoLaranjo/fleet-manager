package dev.laranjo.truckapi.truck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface TruckRepository extends JpaRepository<Truck, Long> {
}
