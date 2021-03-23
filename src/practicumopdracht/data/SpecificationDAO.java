package practicumopdracht.data;

import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public abstract class SpecificationDAO implements DAO<Specification> {
    protected List<Specification> objects = new ArrayList<>();

    public SpecificationDAO() {
        load();
    }

    @Override
    public List<Specification> getAll() {
        return Collections.unmodifiableList(objects);
    }

    /**
     * haal alle objecten op die bij een masterID horen
     * @param smartphone
     * @return
     */
    public List<Specification> getAllFor(Smartphone smartphone) {
        List<Specification> temporaryList = new ArrayList<>();

        for(Specification specification : objects) {
            if(smartphone == specification.getHoortBij()) {
                temporaryList.add(specification);
            }
        }
        return temporaryList;
    }

    public Specification getById(int id) {
        return objects.get(id);
    }

    @Override
    public void addOrUpdate(Specification object) {
        if(!objects.contains(object)) {
            objects.add(object);
        } else {
            int index = objects.indexOf(object);
            objects.set(index, object);
        }
    }

    @Override
    public void remove(Specification object) {
        objects.remove(object);
    }

    public abstract boolean load();

    public abstract boolean save();
}
