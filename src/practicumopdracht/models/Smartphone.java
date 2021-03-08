package practicumopdracht.models;

import java.time.LocalDate;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class Smartphone {
    private int id;
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

    public Object getSerie() {
        return serie;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getVersion() {
        return version;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Smartphone name: ").append(smartphoneName).append("\n");
        result.append("Smartphone serie: ").append(serie).append("\n");
        result.append("Version: ").append(version).append("\n");
        result.append("Date release: ").append(releaseDate).append("\n");

        return result.toString();
    }
}
