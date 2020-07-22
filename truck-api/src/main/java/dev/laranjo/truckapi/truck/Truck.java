package dev.laranjo.truckapi.truck;

import dev.laranjo.truckapi.shared.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
final class Truck extends BaseEntity {

    private String manufacturer;
    private String licensePlate;
    private int year;
    private int month;

    @OneToMany
    private List<GeoRecord> path;

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

    public static class Builder {

        public Truck build() {
            return null;
        }
    }
}
