package practicumopdracht.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class Smartphone {
    private String merk;
    private String model;
    private ArrayList<Specification> specificaties;
    private LocalDate datumUitgave;

    // als het niet erg is als er geen gegevens zijn
    // lege constructor gebruiken
//    public Smartphone() {
//
//    }

    public Smartphone(String merk, String model, LocalDate datumUitgave) {
        this.merk = merk;
        this.model = model;
        specificaties = new ArrayList<>();
        this.datumUitgave = datumUitgave;
    }

    public String getMerk() {
        return merk;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getDatumUitgave() {
        return datumUitgave;
    }

    // met een setter kan je dingen wijzigen
    // met een getter niet
}
