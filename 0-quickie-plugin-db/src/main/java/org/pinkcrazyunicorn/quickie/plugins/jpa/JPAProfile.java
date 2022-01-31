package org.pinkcrazyunicorn.quickie.plugins.jpa;

import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentProfile;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "profile")
public class JPAProfile implements PersistentProfile {
    @Id
    String name;

    @ElementCollection
    Map<String, String> opinions;

    @ElementCollection
    Set<String> available;

    public JPAProfile() { }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, String> getOpinions() {
        return opinions;
    }

    @Override
    public Set<String> getAvailable() {
        return available;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAvailable(Set<String> stock) {
        this.available = stock;
    }

    @Override
    public void setOpinions(Map<String, String> opinions) {
        this.opinions = opinions;
    }
}
