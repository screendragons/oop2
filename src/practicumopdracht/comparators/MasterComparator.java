package practicumopdracht.comparators;

import practicumopdracht.models.Smartphone;

import java.util.Comparator;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class MasterComparator implements Comparator<Smartphone> {
    private boolean SORTZA;

    public MasterComparator(boolean sortDescending) {
        this.SORTZA = sortDescending;
    }

    @Override
    public int compare(Smartphone o1, Smartphone o2) {
        final int RESULT = o1.getSmartphoneName().toLowerCase().compareTo(o2.getSmartphoneName().toLowerCase());
        final int MIN_RESULT = 0;
        // sort ascending
        // if the result is the same than sort ascending by version
        if (RESULT == MIN_RESULT) {
            return Integer.compare(o1.getVersion(), o2.getVersion());
        }
        // sort descending
        if (SORTZA) {
            return -RESULT;
        }
        return RESULT;
    }
}