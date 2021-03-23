package practicumopdracht.data;

import practicumopdracht.MainApplication;
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
        Specification specification1 = new Specification(6, 15, 7,0.1, true,
                "iOS", "Een mooie smartphone",
                MainApplication.getSmartphoneDAO().getById(0));

        Specification specification2 = new Specification(7, 17, 6,0.3, true,
                "Android", "Een coole smartphone",
                MainApplication.getSmartphoneDAO().getById(1));

        Specification specification3 = new Specification(8, 14, 5,0.3, true,
                "Android", "Een toffe smartphone",
                MainApplication.getSmartphoneDAO().getById(2));

        addOrUpdate(specification1);
        addOrUpdate(specification2);
        addOrUpdate(specification3);

        return true;
    }
}