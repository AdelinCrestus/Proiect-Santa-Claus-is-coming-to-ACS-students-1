package copy;

import gifts.Gift;

import java.util.ArrayList;

public class CopyGifts {
    public ArrayList<Gift> copyGiftList(ArrayList<Gift> gifts) {
        ArrayList<Gift> copy = new ArrayList<>();
        for(Gift gift : gifts) {
            copy.add(new Gift(gift));
        }
        return copy;
    }
}
