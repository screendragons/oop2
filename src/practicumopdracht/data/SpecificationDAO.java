package practicumopdracht.data;

import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Functionality: DAO for the detail
 *
 * @author Chi Yu Yeung
 */
public abstract class SpecificationDAO implements DAO<Specification> {
    protected List<Specification> objects;

    /**
     * Get the objects of an arraylist and load
     */
    public SpecificationDAO() {
        objects = new ArrayList<>();
        load();
    }

    /**
     * Get all the objects
     *
     * @return
     */
    @Override
    public List<Specification> getAll() {
        return Collections.unmodifiableList(objects);
    }

    /**
     * Get all the objects that belongs to the master
     *
     * @param smartphone
     * @return
     */
    public List<Specification> getAllFor(Smartphone smartphone) {
        List<Specification> temporaryList = new ArrayList<>();

        for (Specification specification : objects) {
            if (smartphone == specification.getHoortBij()) {
                temporaryList.add(specification);
            }
        }
        return temporaryList;
    }

    /**
     * Get item by id
     *
     * @param id
     * @return
     */
    public Specification getById(int id) {
        // if the array is empty return null
        if (objects.size() == 0) {
            return null;
        }
        // else return the id
        return objects.get(id);
    }

    /**
     * Add or update
     *
     * @param object
     */
    @Override
    public void addOrUpdate(Specification object) {
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
    public void remove(Specification object) {
        objects.remove(object);
    }

    public abstract boolean load();

    public abstract boolean save();
}
