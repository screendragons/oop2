package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Functionality: The TextDAO of the detail
 *
 * @author Chi Yu Yeung
 */
public class TextSpecificationDAO extends SpecificationDAO {
    private static final String FILENAME = "specifications.txt";
    final int NOTE_POSITION = 7;

    /**
     * Save the information
     *
     * @return
     */
    @Override
    public boolean save() {
        File file = new File(FILENAME);

        try {
            PrintWriter printWriter = new PrintWriter(file);

            for (Specification specification : objects) {
                int idFor = MainApplication.getSmartphoneDAO().getIdFor(specification.getHoortBij());
                printWriter.print(idFor + ",");
                printWriter.print(specification.getInch() + ",");
                printWriter.print(specification.getHeight() + ",");
                printWriter.print(specification.getWidth() + ",");
                printWriter.print(specification.getThickness() + ",");
                printWriter.print(specification.isFingerprintSensor() + ",");
                printWriter.print(specification.getOperatingSystem() + ",");
                printWriter.print(specification.getNote() + " \n");
            }
            printWriter.close();

        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + "Specification save bestand niet gevonden!");
            return false;
        }
        return true;
    }

    /**
     * Load the information
     *
     * @return
     */
    @Override
    public boolean load() {
        File file = new File(FILENAME);
        // empty the list
        objects.clear();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String output = scanner.nextLine();
                String[] textSpecification = output.split(",");

                int idFor = Integer.parseInt(textSpecification[0]);
                Smartphone hoortBij = MainApplication.getSmartphoneDAO().getById(idFor);

                double inch = Double.parseDouble(textSpecification[1]);
                double height = Double.parseDouble(textSpecification[2]);
                double width = Double.parseDouble(textSpecification[3]);
                double thickness = Double.parseDouble(textSpecification[4]);
                boolean fingerprintSensor = Boolean.parseBoolean(textSpecification[5]);

                Object operatingSystem = textSpecification[6];
                //
                String note = (textSpecification.length > NOTE_POSITION) ? textSpecification[7] : "";

                Specification specification = new Specification(
                        hoortBij, inch, height, width, thickness, fingerprintSensor, operatingSystem, note
                );
                addOrUpdate(specification);
            }
        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + "Specifications load bestand niet gevonden!");
            return false;
        }
        return true;
    }

}
