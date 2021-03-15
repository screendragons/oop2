package practicumopdracht.data;

import practicumopdracht.models.Specification;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class FakeSpecificationDAO extends SpecificationDAO {
    @Override
    public boolean save() {
        return true;
    }

    @Override
    public boolean load() {
        Specification specification1 = new Specification(1.00, 2.00, 4,0.1, true,
                "iOS", "Een mooie smartphone", 1);
        addOrUpdate(specification1);

        return true;
    }
}