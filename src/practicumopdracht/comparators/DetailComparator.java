package practicumopdracht.comparators;

import practicumopdracht.models.Specification;

import java.util.Comparator;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class DetailComparator implements Comparator<Specification> {
    private boolean SORTZA;
    // inner classes
    public DetailComparator (boolean sortDescending) {
            this.SORTZA = sortDescending;
    }

    public int compare(Specification o1, Specification o2) {
        return Double.compare(o1.getInch(), o2.getInch());
    }

//    public static double SortAscName {
//
//        @Override
//        public int compare(Specification o1, Specification o2) {
//            final int RESULT = Double.compare(o1.getInch(), o2.getInch());
//            if ( RESULT == 0) {
//                // TODO extra compare
//                return -1;
//            } else {
//                return RESULT;
//            }
//        }
//    }
}
