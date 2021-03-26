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

    public DetailComparator(boolean sortDescending) {
        this.SORTZA = sortDescending;
    }

    @Override
    public int compare(Specification o1, Specification o2) {
        final int RESULT = Double.compare(o1.getInch(), o2.getInch());
        final int MIN_RESULT = 0;
        // sort ascending
        // if the result is the same as sort ascending with inch
        // ascend the output with height
        if (RESULT == MIN_RESULT) {
            return Double.compare(o1.getHeight(), o2.getHeight());
        }
        // sort descending
        if (SORTZA) {
            return -RESULT;
        }
        return RESULT;
    }
}