package data;

import children.Child;
import gifts.Gift;

import java.util.ArrayList;

public final class InitialData {
    private ArrayList<Child> children;
    private ArrayList<Gift> santaGiftsList;

    public InitialData() {
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(final ArrayList<Child> children) {
        this.children = children;
    }

    public ArrayList<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final ArrayList<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

}
