package com.oreilly.quest.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Castle {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String city;
    private String state;

    @Min(-90) @Max(90)
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "castle", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Knight> knights = new HashSet<>();

    public Castle() {
    }

    public Castle(String name) {
        this.name = name;
    }

    public Castle(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public Set<Knight> getKnights() {
        return knights;
    }

    public Castle addToKnights(Knight knight) {
        knights.add(knight);
        knight.setCastle(this);
        return this;
    }

    public void removeKnight(Knight knight) {
        knights.remove(knight);
        knight.setCastle(null);
    }

    public void setKnights(Set<Knight> knights) {
        this.knights = knights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Castle castle = (Castle) o;
        return Double.compare(castle.latitude, latitude) == 0 && Double.compare(castle.longitude, longitude) == 0 && Objects.equals(id, castle.id) && Objects.equals(name, castle.name) && Objects.equals(city, castle.city) && Objects.equals(state, castle.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, state, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Castle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
