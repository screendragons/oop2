package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.util.List;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class FakeSpecificationDAO extends SpecificationDAO {

    @Override
    public List<Smartphone> getAll() {
        return null;
    }

    @Override
    public void remove(Smartphone object) {

    }

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }
}
