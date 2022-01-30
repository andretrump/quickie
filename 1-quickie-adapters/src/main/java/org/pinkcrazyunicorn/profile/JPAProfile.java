package org.pinkcrazyunicorn.profile;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "profile")
public class JPAProfile {
    @Id
    String name;

    @ElementCollection
    Map<String, String> opinions;

    @ElementCollection
    List<String> stock;

    public JPAProfile() { }

    public JPAProfile(String name, Map<String, String> opinions, List<String> stock) {
        this.name = name;
        this.opinions = opinions;
        this.stock = stock;
    }

    public JPAProfile(String name) {
        this.name = name;
        this.opinions = new HashMap<>();
        this.stock = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getOpinions() {
        return opinions;
    }

    public List<String> getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(List<String> stock) {
        this.stock = stock;
    }

    public void setOpinions(Map<String, String> opinions) {
        this.opinions = opinions;
    }
}
