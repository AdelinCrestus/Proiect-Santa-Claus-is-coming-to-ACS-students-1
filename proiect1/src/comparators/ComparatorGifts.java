package comparators;

import gifts.Gift;

import java.util.Comparator;

public final class ComparatorGifts implements Comparator<Gift> {

    @Override
    public int compare(final Gift o1, final Gift o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
