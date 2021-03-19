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
    protected List<Specification> objects;

    public SpecificationDAO() {
        this.objects = new ArrayList<>();
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
        for (Specification specification : objects) {
//            if(specification.getId() == id) {
//                return specification;
//            }

            // TODO wat is het verschil tussen hoortbij en getId?
//            if(id == specification.getHoortBij()) {
//                return specification;
//            }
        }
        return null;
    }

    @Override
    public void addOrUpdate(Specification object) {
        if(!objects.contains(object)) {
            objects.add(object);
        }
    }

    @Override
    public void remove(Specification object) {
//        Specification foundSpecification = getById(object.getId());
//
//        if(foundSpecification != null) {
//            objects.remove(foundSpecification);
//        }
    }

    public abstract boolean load();

    public abstract boolean save();
}
