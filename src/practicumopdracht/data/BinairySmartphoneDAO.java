package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.io.*;
import java.time.LocalDate;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class BinairySmartphoneDAO extends SmartphoneDAO{
    private final String FILENAME = "smartphones.dat";

    @Override
    public boolean save() {
        File file = new File(FILENAME);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            // with dataoutputstream you can work with binairy data
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.writeInt(objects.size());

            for(Smartphone smartphone : objects) {
                dataOutputStream.writeUTF(smartphone.getSmartphoneName());
                dataOutputStream.writeUTF((String) smartphone.getSerie());
                dataOutputStream.writeInt(smartphone.getVersion());
                LocalDate releaseDate = smartphone.getReleaseDate();
                dataOutputStream.writeUTF(String.valueOf(releaseDate));
            }

            dataOutputStream.close();

        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + "Binairy save bestand niet gevonden!");
            return false;

        }
        return true;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);

        objects.clear();
        try {
            // read something, so inputstream
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            int amountSmartphones = dataInputStream.readInt();

            for (int i = 0; i < amountSmartphones; i++) {
                String smartphoneName = dataInputStream.readUTF();
                Object serie = dataInputStream.readUTF();
                int version = dataInputStream.readInt();
                String release = dataInputStream.readUTF();
                LocalDate releaseDate = LocalDate.parse(release);

                addOrUpdate(new Smartphone(smartphoneName, (String) serie, version, releaseDate));
            }

            // the file can be used for the second time after running the function
            dataInputStream.close();

        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + "Binairy load bestand niet gevonden!");
            return false;
        }
        return true;
    }
}
