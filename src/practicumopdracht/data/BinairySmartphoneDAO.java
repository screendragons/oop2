package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.io.*;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class BinairySmartphoneDAO extends SmartphoneDAO{
    // TODO ik snap die IO niet lol
    private final String FILENAME = "smartphones.dat";

    @Override
    public boolean save() {
        File file = new File(FILENAME);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.write(objects.size());

            for(Smartphone smartphone : objects) {
                dataOutputStream.writeInt(getIdFor(smartphone));
                dataOutputStream.writeUTF(smartphone.getSmartphoneName());
                dataOutputStream.writeUTF((String) smartphone.getSerie());
//                dataOutputStream.writeUTF(Integer.parseInt(smartphone.getVersion()));
//                dataOutputStream.writeUTF(LocalDate.parse(smartphone.getReleaseDate()));
            }

            dataOutputStream.close();

        } catch (Exception e) {
//            System.err.println(e.toString() + "\n" + "Smartphone save bestand niet gevonden!");
            return false;

        }
        return true;
    }

    // TODO load functie werkt niet
    @Override
    public boolean load() {
        File file = new File(FILENAME);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            int amountSmartphones = dataInputStream.readInt();

            for (int i = 0; i < amountSmartphones; i++) {
                String smartphoneName = dataInputStream.readUTF();
                Object serie = dataInputStream.readUTF();
                int version = Integer.parseInt(dataInputStream.readUTF());
//                LocalDate releaseDate = dataInputStream.readUTF();
//                addOrUpdate(new Smartphone(smartphoneName, (String) serie, version, releaseDate));
            }

        } catch (Exception e) {
//            System.err.println(e.toString() + "\n" + "Smartphone load bestand niet gevonden!");
            return false;
        }
        return true;
    }
}
