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

//            printWriter.println(objects.size());

            for(Smartphone smartphone : objects) {
                printWriter.println(smartphone.getId());
                printWriter.print(smartphone.getSmartphoneName());
                printWriter.print(smartphone.getSerie());
                printWriter.print(smartphone.getVersion());
                printWriter.print(smartphone.getReleaseDate());
            }

            printWriter.close();

            return true;
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                int id = Integer.parseInt(scanner.nextLine());
                String smartphoneName = scanner.nextLine();
                Object serie = scanner.nextLine();
                int version = Integer.parseInt(scanner.nextLine());
                LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

                Smartphone smartphone = new Smartphone(smartphoneName, (String) serie, version, releaseDate);
                objects.add(smartphone);
            }
        } catch (Exception e) {

        }

        return false;
    }

}
