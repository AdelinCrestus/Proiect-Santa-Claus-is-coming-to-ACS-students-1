package comparators;

import gifts.Gift;

import java.util.Comparator;

public class ComparatorGifts implements Comparator<Gift> {

    @Override
    public int compare(Gift o1, Gift o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
