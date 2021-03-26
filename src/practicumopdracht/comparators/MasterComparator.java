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
//
//    public int compare(Fisherman o1, Fisherman o2) {
//        // bert aap
//        // get int of result
//        // using toLowercase because highercase has a priority above lowercase
//        int compareFirstname = o1.getFirstname().toLowerCase().compareTo(
//                o2.getFirstname().toLowerCase()
//        );
//        // sort a/z
//        // if equal 0
//        // if first is first -1
//        // if last is first 1
//        final int MIN_RESULT = 0;
//        // if firstname is equal sort on lastname
//        if (compareFirstname == MIN_RESULT) {
//            return o1.getLastname().toLowerCase().compareTo(o2.getLastname().toLowerCase());
//        }
//        if (sortZA) {
//            // sort descending
//            return -compareFirstname;
//        }
//        return compareFirstname;
//    }
//}
