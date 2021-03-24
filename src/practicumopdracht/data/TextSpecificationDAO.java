package practicumopdracht.data;

import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class TextSpecificationDAO extends SpecificationDAO{
    private static final String FILENAME = "specifications.txt";

    @Override
    public boolean save() {
        File file = new File(FILENAME);

        try {
            PrintWriter printWriter = new PrintWriter(file);

            for (Specification specification : objects) {
                printWriter.print(specification.getInch() + ", ");
                printWriter.print(specification.getHeight() + ", ");
                printWriter.print(specification.getWidth() + ", ");
                printWriter.print(specification.getThickness() + ", ");
                printWriter.print(specification.isFingerprintSensor() + ", ");
                printWriter.print(specification.getOperatingSystem() + ", ");
                printWriter.print(specification.getNote() + "\n");
            }

            printWriter.close();
        } catch (Exception e) {
//            System.err.println(e.toString() + "\n" + "Specification save bestand niet gevonden!");
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
            while (scanner.hasNext()) {
                double inch = Double.parseDouble(scanner.nextLine());
                double height = Double.parseDouble(scanner.nextLine());
                double width = Double.parseDouble(scanner.nextLine());
                double thickness = Double.parseDouble(scanner.nextLine());
                boolean fingerprintSensor = Boolean.parseBoolean(scanner.nextLine());
                Object operatingSystem = scanner.nextLine();
                String note = scanner.nextLine();
//                // TODO welke scanner hoort erbij hoortBij?
//                Smartphone hoortBij = smartphone;
//
//                Specification specification = new Specification(inch, height, width , thickness, fingerprintSensor, operatingSystem, note, hoortBij);
//                addOrUpdate(specification);
            }
        } catch (Exception e) {
//            System.err.println(e.toString() + "\n" + "Smartphone load bestand niet gevonden!");
            return false;
        }

        return true;
    }

}
