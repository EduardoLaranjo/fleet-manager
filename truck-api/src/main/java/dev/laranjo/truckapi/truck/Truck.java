package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
final class Truck extends BaseEntity {


    @Column(unique = true)
    private String licensePlate;
    private String manufacturer;
    private int year;
    private int month;

    @OneToMany(mappedBy = "truck")
    private List<GeoRecord> path;

    public Truck() {
    }

    public Truck(String licensePlate, String manufacturer, int year, int month, List<GeoRecord> path) {
        this.licensePlate = licensePlate;
        this.manufacturer = manufacturer;
        this.year = year;
        this.month = month;
        this.path = path;
    }

    private Truck(long id, LocalDateTime creationDate, String manufacturer, String licensePlate, int year, int month, List<GeoRecord> path) {
        super(id, creationDate);
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.year = year;
        this.month = month;
        this.path = path;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<GeoRecord> getPath() {
        return path;
    }

    public void setPath(List<GeoRecord> path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Truck truck = (Truck) o;
        return getYear() == truck.getYear() &&
                getMonth() == truck.getMonth() &&
                Objects.equals(getManufacturer(), truck.getManufacturer()) &&
                Objects.equals(getLicensePlate(), truck.getLicensePlate()) &&
                Objects.equals(getPath(), truck.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getManufacturer(), getLicensePlate(), getYear(), getMonth(), getPath());
    }

    static class Builder {

        private long id;
        private LocalDateTime creationDate;
        private String manufacturer;
        private String licensePlate;
        private int year;
        private int month;
        private List<GeoRecord> path;

        public Truck build() {
            final var truck = new Truck(id, creationDate, manufacturer, licensePlate, year, month, path);
            return truck;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder licensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder month(int month) {
            this.month = month;
            return this;
        }

        public Builder path(List<GeoRecord> path) {
            this.path = path;
            return this;
        }

    }
}
