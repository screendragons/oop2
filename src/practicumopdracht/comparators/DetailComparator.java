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