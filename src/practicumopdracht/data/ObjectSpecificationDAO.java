package practicumopdracht.data;

import practicumopdracht.models.Specification;

import java.io.*;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class ObjectSpecificationDAO extends SpecificationDAO {
    private final String FILENAME = "specification.obj";

    @Override
    public boolean save() {
        File file = new File(FILENAME);

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeInt(objects.size());

            for (Specification specification : objects) {
                objectOutputStream.writeObject(specification);
            }
        } catch (Exception e) {
            System.err.println("Object save function error");
        }
        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);

        objects.clear();

        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            int amountSpecifications = objectInputStream.readInt();

            for (int i = 0; i < amountSpecifications; i++) {
                Specification specification = (Specification) objectInputStream.readObject();
                addOrUpdate(specification);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Object load function error 1");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Object load function error 2");
        }
        return false;
    }
}
