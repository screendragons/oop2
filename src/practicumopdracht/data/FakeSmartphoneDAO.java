package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.time.LocalDate;

/**
 * Functionality: Fake master DAO
 *
 * @author Chi Yu Yeung
 */
public class FakeSmartphoneDAO extends SmartphoneDAO {
    /**
     * Save function from the master DAO
     *
     * @return
     */
    @Override
    public boolean save() {
        return true;
    }

    /**
     * Load function from the master DAO with fake data
     *
     * @return
     */
    @Override
    public boolean load() {
        // clears the object list and adds the fake dao information
        objects.clear();

        Smartphone smartphone1 = new Smartphone(
                "Samsung S21", "S series", 1,
                LocalDate.of(2021, 1, 14)
        );

        Smartphone smartphone2 = new Smartphone(
                "OnePlus 8T", "T series", 1,
                LocalDate.of(2020, 10, 30)
        );

        Smartphone smartphone3 = new Smartphone(
                "iPhone 12 Pro", "12 series", 1,
                LocalDate.of(2020, 10, 13)
        );

        addOrUpdate(smartphone1);
        addOrUpdate(smartphone2);
        addOrUpdate(smartphone3);

        return true;
    }
}
