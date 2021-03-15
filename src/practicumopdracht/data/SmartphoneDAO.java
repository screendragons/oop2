package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public abstract class SmartphoneDAO implements DAO<Smartphone> {
    protected List<Smartphone> objects;

    public SmartphoneDAO() {
        objects = new ArrayList<>();
        load();
    }

    @Override
    public List<Smartphone> getAll() {
        return Collections.unmodifiableList(objects);
    }

    public Smartphone getById(int id) {
        for (Smartphone smartphone : objects) {
            if(smartphone.getId() == id) {
                return smartphone;
            }
        }
        return null;
    }

    @Override
    public void addOrUpdate(Smartphone object) {
        if(!objects.contains(object)) {
            objects.add(object);
        }
    }

    @Override
    public void remove(Smartphone object) {
        Smartphone foundSmartphone = getById(object.getId());

        if(foundSmartphone != null) {
            objects.remove(foundSmartphone);
        }
    }

    private int getUniqueId() {
        int highestId = 0;

        for(Smartphone smartphone: objects) {
            if(smartphone.getId() > highestId) {
                highestId = smartphone.getId();
            }
        }
        return highestId + 1;
    }

    @Override
    public abstract boolean load();

    @Override
    public abstract boolean save();
}
