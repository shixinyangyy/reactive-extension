package org.yugh.domainevent.boot.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    private String city;
    private String country;
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID")
    private List<Store> stores = new ArrayList<>();

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Long getId() {
        return id;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
