package practicumopdracht.data;

import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;

import java.util.List;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public abstract class SpecificationDAO implements DAO {
    protected List<Specification> objects;

//    public List<Specification> getAllFor(Smartphone object) {
//
//    }

    public abstract boolean load();

    public abstract boolean save();
}
