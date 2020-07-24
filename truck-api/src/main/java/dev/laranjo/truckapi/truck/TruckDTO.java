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
        private final double lat;
        private final double log;

        public Coordinate(double lat, double log) {
            this.lat = lat;
            this.log = log;
        }

        public double getLat() {
            return lat;
        }

        public double getLog() {
            return log;
        }
    }
}
