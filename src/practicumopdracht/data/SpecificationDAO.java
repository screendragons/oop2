package practicumopdracht.data;

import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public abstract class SpecificationDAO implements DAO<Smartphone> {
    protected List<Specification> objects;

    public SpecificationDAO() {
        this.objects = new ArrayList<>();
    }

    public List<Specification> getAllFor(Smartphone object) {
        return this.objects;
    }

    @Override
    public void addOrUpdate(Smartphone object) {

    }

    public abstract boolean load();

    public abstract boolean save();
}
