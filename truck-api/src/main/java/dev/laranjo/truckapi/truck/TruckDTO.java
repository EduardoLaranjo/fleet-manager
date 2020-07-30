package dev.laranjo.truckapi.truck;

import java.util.List;

class TruckDTO {

    private final int year;
    private final int month;
    private final String licensePlate;
    private List<Coordinate> path;

    public TruckDTO(String licensePlate, int month, int year, List<Coordinate> path) {
        this.licensePlate = licensePlate;
        this.month = month;
        this.year = year;
        this.path = path;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public List<Coordinate> getPath() {
        return path;
    }

    public static class Coordinate {
        private final long id;
        private final double lat;
        private final double lng;

        public Coordinate(long id, double lat, double lng) {
            this.id = id;
            this.lat = lat;
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }

        public long getId() {
            return id;
        }
    }
}
