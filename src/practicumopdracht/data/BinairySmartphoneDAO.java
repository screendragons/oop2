package practicumopdracht.data;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class BinairySmartphoneDAO extends SmartphoneDAO{
    private final String FILENAME = "smaartphones.dot";

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }
}
