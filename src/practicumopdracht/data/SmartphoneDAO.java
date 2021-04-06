package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.util.ArrayList;
import java.util.List;

/**
 * Functionality: DAO for the master
 *
 * @author Chi Yu Yeung
 */
public abstract class SmartphoneDAO implements DAO<Smartphone> {
    protected List<Smartphone> objects;

    /**
     * Get the objects of an arraylist and load
     */
    public SmartphoneDAO() {
        objects = new ArrayList<>();
        load();
    }

    /**
     * Get all the objects
     *
     * @return
     */
    @Override
    public List<Smartphone> getAll() {
        return this.objects;
    }

    /**
     * Get item by id
     *
     * @param index
     * @return
     */
    public Smartphone getById(int index) {
        // if the array is empty return null
        if (objects.size() == 0) {
            return null;
        }
        // else return the index
        return objects.get(index);
    }

    /**
     * Get the object that belongs to the index
     *
     * @param object
     * @return
     */
    public int getIdFor(Smartphone object) {
        // if there is the object in the objects list
        // return the index of the object
        if (objects.contains(object)) {
            return objects.indexOf(object);
            // else return -1
        } else {
            return -1;
        }
    }

    /**
     * Add or update
     *
     * @param object
     */
    @Override
    public void addOrUpdate(Smartphone object) {
        // if the objects arraylist doesn't contain the object
        // add the object
        if (!objects.contains(object)) {
            objects.add(object);
            // else set the object at the index
        } else {
            int index = objects.indexOf(object);
            objects.set(index, object);
        }
    }

    /**
     * Remove the object of the objects list
     *
     * @param object
     */
    @Override
    public void remove(Smartphone object) {
        objects.remove(object);
    }

    @Override
    public abstract boolean load();

    @Override
    public abstract boolean save();
}
