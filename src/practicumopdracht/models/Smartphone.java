package practicumopdracht.models;

import java.time.LocalDate;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class Smartphone {
    private String smartphoneName;
    private Object serie;
    private LocalDate releaseDate;

    public Smartphone(String smartphoneName, String serie, LocalDate releaseDate) {
        this.smartphoneName = smartphoneName;
        this.serie = serie;
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

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Smartphone name: ").append(smartphoneName).append("\n");
        result.append("Smartphone serie: ").append(serie).append("\n");
        result.append("Date release: ").append(releaseDate).append("\n");

        return result.toString();
    }
}
