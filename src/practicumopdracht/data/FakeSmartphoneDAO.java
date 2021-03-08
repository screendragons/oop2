package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.time.LocalDate;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class FakeSmartphoneDAO extends SmartphoneDAO{
    @Override
    public boolean save() {
//        Smartphone smartphone1 = new Smartphone("Samsung S21", "S series", 1,
//                LocalDate.of(2021, 1, 14));
//        addOrUpdate(smartphone1);
        return true;
    }

    @Override
    public boolean load() {
        Smartphone smartphone1 = new Smartphone("Samsung S21", "S series", 1,
                LocalDate.of(2021, 1, 14));
        Smartphone smartphone2 = new Smartphone("OnePlus 8T", "T series", 1,
                LocalDate.of(2020, 10, 30));
        Smartphone smartphone3 = new Smartphone("iPhone 12 Pro", "12 series", 1,
                LocalDate.of(2020, 10, 13));
        addOrUpdate(smartphone1);
        addOrUpdate(smartphone2);
        addOrUpdate(smartphone3);

        return true;
    }

}
