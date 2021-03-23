package practicumopdracht.data;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class ObjectSpecificationDAO extends SpecificationDAO{
    private final String FILENAME = "smaartphones.dat";

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }
}
