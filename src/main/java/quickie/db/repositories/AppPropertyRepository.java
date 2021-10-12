package quickie.db.repositories;

import quickie.db.types.Property;
import quickie.db.exceptions.InvalidPropertyValue;
import quickie.db.exceptions.PropertyNotSet;
import quickie.db.entities.AppProperty;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AppPropertyRepository {
    private final EntityManager em;

    public AppPropertyRepository(EntityManager em) {
        this.em = em;
    }

    public void updateLatestDownload() {
        this.setProperty(Property.LATEST_DOWNLOAD, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    public LocalDateTime getLatestDownload() throws PropertyNotSet, InvalidPropertyValue {
        this.em.getTransaction().begin();
        AppProperty propertyEntity = em.find(AppProperty.class, Property.LATEST_DOWNLOAD.getText());
        this.em.getTransaction().commit();

        if (propertyEntity == null) {
            throw new PropertyNotSet("Property " + Property.LATEST_DOWNLOAD.getText() + " is not set.");
        } else {
            return LocalDateTime.parse(propertyEntity.getValue(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
    }

    public boolean downloadAllowed() {
        LocalDateTime latestDownload;
        try {
            latestDownload = this.getLatestDownload();
        } catch (PropertyNotSet e) {
            System.out.println("Info: Pages not downloaded yet");
            return true;
        } catch (InvalidPropertyValue e) {
            System.out.println("Warning: Invalid value for latestDownload");
            return true;
        }
        long timeDiff = ChronoUnit.HOURS.between(latestDownload, LocalDateTime.now());
        return timeDiff > 24;
    }

    private void setProperty(Property property, String value) {
        this.em.getTransaction().begin();
        AppProperty propertyEntity = this.em.find(AppProperty.class, property.getText());
        if (propertyEntity == null) {
            propertyEntity = new AppProperty(property.getText(), value);
        } else {
            propertyEntity.setValue(value);
        }
        this.em.persist(propertyEntity);
        this.em.getTransaction().commit();
    }

    public boolean propertyIsSet(Property property) {
        this.em.getTransaction().begin();
        AppProperty propertyEntity = this.em.find(AppProperty.class, property.getText());
        this.em.getTransaction().commit();

        return propertyEntity != null;
    }
}
