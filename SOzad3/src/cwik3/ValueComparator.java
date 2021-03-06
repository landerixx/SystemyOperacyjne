package cwik3;

import java.util.Comparator;
import java.util.Map;

class ValueComparator implements Comparator<Integer> {

    Map<Integer, Integer> base;
    public ValueComparator(Map<Integer, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(Integer va, Integer vb) {
        if (base.get(va) >= base.get(vb)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}