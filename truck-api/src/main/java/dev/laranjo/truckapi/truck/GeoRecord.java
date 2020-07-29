package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
final class GeoRecord extends BaseEntity {

    private double lat;
    private double lng;

    @ManyToOne(fetch = FetchType.LAZY)
    private Truck truck;

    public GeoRecord() {
    }

    public GeoRecord(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public static GeoRecord at(double lat, double log) {
        return new GeoRecord(lat, log);
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
