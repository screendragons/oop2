package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Specification;

/**
 * Functionality: Fake detail DAO
 *
 * @author Chi Yu Yeung
 */
public class FakeSpecificationDAO extends SpecificationDAO {
    /**
     * Save function from the detail DAO
     *
     * @return
     */
    @Override
    public boolean save() {
        return true;
    }

    /**
     * Load function from the detail DAO with fake data
     *
     * @return
     */
    @Override
    public boolean load() {
        // clears the object list and adds the fake dao information
        objects.clear();

        Specification specification1 = new Specification(
                MainApplication.getSmartphoneDAO().getById(0),
                6, 15, 7, 0.1, true,
                "iOS", "Een mooie smartphone"
        );

        Specification specification2 = new Specification(
                MainApplication.getSmartphoneDAO().getById(1),
                7, 17, 6, 0.3, true,
                "Android", "Een coole smartphone"
        );

        Specification specification3 = new Specification(
                MainApplication.getSmartphoneDAO().getById(2),
                8, 14, 5, 0.3, true,
                "Android", "Een toffe smartphone"
        );

        addOrUpdate(specification1);
        addOrUpdate(specification2);
        addOrUpdate(specification3);

        return true;
    }
}