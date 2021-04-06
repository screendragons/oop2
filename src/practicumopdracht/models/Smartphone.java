package practicumopdracht.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Functionality: Master model
 *
 * @author Chi Yu Yeung
 */
public class Smartphone implements Serializable {
    private String smartphoneName;
    private Object serie;
    private int version;
    private LocalDate releaseDate;

    /**
     * Constructor to define all the information
     *
     * @param smartphoneName the name
     * @param serie          the series
     * @param version        the version
     * @param releaseDate    the release date
     */
    public Smartphone(String smartphoneName, String serie, int version, LocalDate releaseDate) {
        this.smartphoneName = smartphoneName;
        this.serie = serie;
        this.version = version;
        this.releaseDate = releaseDate;
    }

    /**
     * Get the smartphone name
     *
     * @return
     */
    public String getSmartphoneName() {
        return smartphoneName;
    }

    /**
     * Set the smartphonename so it can be edited
     *
     * @param smartphoneName the name
     */
    public void setSmartphoneName(String smartphoneName) {
        this.smartphoneName = smartphoneName;
    }

    /**
     * Get the serie
     *
     * @return
     */
    public Object getSerie() {
        return serie;
    }

    /**
     * Set the serie so it can be edited
     *
     * @param serie the serie
     */
    public void setSerie(Object serie) {
        this.serie = serie;
    }

    /**
     * Get the version
     *
     * @return
     */
    public int getVersion() {
        return version;
    }

    /**
     * Set the version so it can be edited
     *
     * @param version the version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Get the release date
     *
     * @return
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Set the release date so it can be edited
     *
     * @param releaseDate the release date
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * The toString method shows the information
     *
     * @return
     */
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
