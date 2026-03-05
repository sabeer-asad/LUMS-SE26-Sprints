package com.example.listycity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a City with a name and a province.
 * It implements Serializable to allow city objects to be passed between activities.
 */
public class City implements Serializable, Comparable {
    private String name;
    private String province;

    /**
     * Constructs a City object with the specified name and province.
     *
     * @param name     The name of the city.
     * @param province The province where the city is located.
     */
    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    /**
     * Returns the name of the city.
     *
     * @return The name of the city.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the province of the city.
     *
     * @return The province of the city.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Compares this city to another city based on their names.
     *
     * @param o The object to compare this city to.
     * @return A negative integer, zero, or a positive integer
     */
    @Override
    public int compareTo(Object o) {
        City city = (City) o;
        return this.name.compareTo(city.getName()); // this.city refers to the city name
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name) && Objects.equals(province, city.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, province);
    }
}
