package practicumopdracht.data;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class TextSpecificationDAO extends SpecificationDAO{
    private static final String FILENAME = "specifications.txt";

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }
}
