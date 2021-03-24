package practicumopdracht.data;

import java.io.File;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class ObjectSpecificationDAO extends SpecificationDAO{
    private final String FILENAME = "specification.dat";

    @Override
    public boolean load() {
        File file = new File(FILENAME);

        return false;
    }

    @Override
    public boolean save() {
        File file = new File(FILENAME);

        return false;
    }
}
