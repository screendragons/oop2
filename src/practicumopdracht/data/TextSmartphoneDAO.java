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
public class TextSmartphoneDAO extends SmartphoneDAO{
    private static final String FILENAME = "smartphones.txt";

    @Override
    public boolean save() {
        File file = new File(FILENAME);

        try {
            PrintWriter printWriter = new PrintWriter(file);

            for(Smartphone smartphone : objects) {
//                printWriter.println(smartphone.getId());
                printWriter.print(smartphone.getSmartphoneName() + ",");
                printWriter.print(smartphone.getSerie() + ",");
                printWriter.print(smartphone.getVersion() + ",");
                printWriter.print(smartphone.getReleaseDate() + "\n");
            }

            printWriter.close();
        } catch (Exception e) {
//            System.err.println(e.toString() + "\n" + "save Bestand niet gevonden!");
            return false;

        }
        return true;
    }

    // TODO load functie werkt niet
    @Override
    public boolean load() {
        File file = new File(FILENAME);
        // empty the list
        objects.clear();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String smartphoneName = scanner.nextLine();
                Object serie = scanner.nextLine();
                int version = Integer.parseInt(scanner.nextLine());
                LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

                Smartphone smartphone = new Smartphone(smartphoneName, (String) serie, version, releaseDate);
                addOrUpdate(smartphone);
            }
        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + "load Bestand niet gevonden!");
            return false;
        }

        return true;
    }

}
