package practicumopdracht.comparators;

import practicumopdracht.models.Smartphone;

import java.util.Comparator;

/**
 * Functionality: The sort function for the master view
 *
 * @author Chi Yu Yeung
 */
public class MasterComparator implements Comparator<Smartphone> {
    private boolean SORTZA;

    /**
     * constructor where sortza is the type of sortdescending
     * @param sortDescending shows that SORTZA is true or false
     */
    public MasterComparator(boolean sortDescending) {
        this.SORTZA = sortDescending;
    }

    /**
     * Compares two items, which is the smartphone name. If they are the same, then it will sort by the version
     * @param o1 first comparator
     * @param o2 second comparator
     * @return
     */
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