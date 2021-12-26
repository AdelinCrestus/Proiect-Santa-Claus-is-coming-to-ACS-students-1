package data;

import children.Child;
import enums.Cities;
import gifts.Gift;

import java.util.ArrayList;

public class InitialData {
    private ArrayList<Child> children;
    private ArrayList<Gift> santaGiftsList;
    private ArrayList<Cities> cities;

    public InitialData() {
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    public ArrayList<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(ArrayList<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    public ArrayList<Cities> getCities() {
        return cities;
    }

    public void setCities(ArrayList<Cities> cities) {
        this.cities = cities;
    }
}
