package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class TextSmartphoneDAO extends SmartphoneDAO {
    private static final String FILENAME = "smartphones.txt";

    @Override
    public boolean save() {
        File file = new File(FILENAME);

        try {
            PrintWriter printWriter = new PrintWriter(file);

            for (Smartphone smartphone : objects) {
                printWriter.print(smartphone.getSmartphoneName() + ",");
                printWriter.print(smartphone.getSerie() + ",");
                printWriter.print(smartphone.getVersion() + ",");
                printWriter.print(smartphone.getReleaseDate() + "\n");
            }

            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + "Smartphone save bestand niet gevonden!");
            return false;

        }
        return true;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        // empty the list
        objects.clear();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String output = scanner.nextLine();
                String[] textSmartphone = output.split(",");
                int version = Integer.parseInt(textSmartphone[2]);
                LocalDate releaseDate = LocalDate.parse(textSmartphone[3]);

                Smartphone smartphone = new Smartphone(textSmartphone[0], textSmartphone[1], version, releaseDate);
                objects.add(smartphone);
            }
        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + "Smartphone load bestand niet gevonden!");
            return false;
        }

        return true;
    }

}
