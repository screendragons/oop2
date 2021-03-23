package practicumopdracht.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class Smartphone implements Serializable {

    private String smartphoneName;
    private Object serie;
    private int version;
    private LocalDate releaseDate;

    public Smartphone(String smartphoneName, String serie, int version, LocalDate releaseDate) {
        this.smartphoneName = smartphoneName;
        this.serie = serie;
        this.version = version;
        this.releaseDate = releaseDate;
    }

    public String getSmartphoneName() {
        return smartphoneName;
    }

    public void setSmartphoneName(String smartphoneName) {
        this.smartphoneName = smartphoneName;
    }

    public Object getSerie() {
        return serie;
    }

    public void setSerie(Object serie) {
        this.serie = serie;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Smartphone name: %s\nSmartphone serie: %s\nVersion: %d\nDate release: %s",
                smartphoneName,
                serie,
                version,
                releaseDate
        );
    }
}
