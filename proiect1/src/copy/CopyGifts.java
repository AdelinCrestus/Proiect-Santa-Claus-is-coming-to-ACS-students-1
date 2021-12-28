package copy;

import gifts.Gift;

import java.util.ArrayList;

public final class CopyGifts {
    /**
     * Creeaza o copie a listei de cadouri gifts
     * @param gifts
     * @return si o returneaza
     */
    public ArrayList<Gift> copyGiftList(final ArrayList<Gift> gifts) {
        ArrayList<Gift> copy = new ArrayList<>();
        for (Gift gift : gifts) {
            copy.add(new Gift(gift));
        }
        return copy;
    }
}
