package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
final class GeoRecord extends BaseEntity {

    private double lat;
    private double lng;

    @ManyToOne(fetch = FetchType.LAZY)
    private Truck truck;

    public GeoRecord() {
    }

    private GeoRecord(Long id, LocalDateTime creationTime, double lat, double lng) {
        super(id, LocalDateTime.now());
        this.lat = lat;
        this.lng = lng;
    }

    public static GeoRecord nowAt(Long id, double lat, double lng) {
        return new GeoRecord(id, LocalDateTime.now(), lat, lng);
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double log) {
        this.lng = log;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
