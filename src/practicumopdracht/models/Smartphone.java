package practicumopdracht.models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class Smartphone {
    private String smartphoneName;
    private String model;
    private ArrayList<Specification> specificaties;
    private LocalDate releaseDate;

    // als het niet erg is als er geen gegevens zijn
    // lege constructor gebruiken
    public Smartphone() {

    }

    public Smartphone(String smartphoneName, String model, LocalDate releaseDate) {
        this.smartphoneName = smartphoneName;
        this.model = model;
        specificaties = new ArrayList<>();
        this.releaseDate = releaseDate;
    }

    public String getSmartphoneName() {
        return smartphoneName;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    // met een setter kan je dingen wijzigen
    // met een getter niet

    public void setModel(String model) {
        this.model = model;
    }

    public void setSpecificaties(ArrayList<Specification> specificaties) {
        this.specificaties = specificaties;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Smartphone merk: ").append(smartphoneName).append("\n");
        result.append("Smartphone model: ").append(model).append("\n");
        result.append("Specificaties: ").append(specificaties).append("\n");
        result.append("Datum uitgave: ").append(releaseDate).append("\n");

        return result.toString();
    }
}
