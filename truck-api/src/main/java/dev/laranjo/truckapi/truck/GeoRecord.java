package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.BaseEntity;

import javax.persistence.Entity;

@Entity
class GeoRecord extends BaseEntity {

    private double lat;
    private double log;

    public GeoRecord() {
    }

    public GeoRecord(double lat, double log) {
        this.lat = lat;
        this.log = log;
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

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
