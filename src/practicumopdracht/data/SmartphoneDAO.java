package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.util.List;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public abstract class SmartphoneDAO implements DAO {
    protected List<Smartphone> objects;

    public void getById(int id) {

    }

    public abstract boolean load();

    public abstract boolean save();
}
