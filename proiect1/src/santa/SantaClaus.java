package santa;

import children.*;
import data.Input;
import gifts.Gift;

import java.util.ArrayList;

public class SantaClaus {
    private static SantaClaus santaClaus;
    private Double budget;
    private ArrayList<Gift> giftList;
    private ArrayList<ChildStrategy> children;
    private Double budgetUnit;

    private SantaClaus() { //singleton
    }

    public static SantaClaus getInstance() {
        if(santaClaus == null) {
            santaClaus = new SantaClaus();
        }
        return santaClaus;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public ArrayList<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(ArrayList<Gift> giftList) {
        this.giftList = giftList;
    }

    public ArrayList<ChildStrategy> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ChildStrategy> children) {
        this.children = children;
    }

    public void set(Input input) {
        santaClaus.setBudget(input.getSantaBudget());
        santaClaus.setGiftList(input.getInitialData().getSantaGiftsList());
        ArrayList<Child> children = input.getInitialData().getChildren();
        for(Child child : children) {
            if(child.getAge() < 5) {
                santaClaus.getChildren().add (
                        new Baby(child.getId(), child.getLastName(),child.getFirstName(),
                                child.getAge(), child.getCity(), child.getNiceScore(),
                                child.getGiftsPreferences()) );

            } else if(child.getAge() >= 5 && child.getAge() < 12) {
                santaClaus.getChildren().add( new Kid(child.getId(),
                        child.getLastName(), child.getFirstName(), child.getAge(),
                        child.getCity(), child.getNiceScore(), child.getGiftsPreferences()));
            } else if(child.getAge() >= 12 && child.getAge() <= 18) {
                santaClaus.getChildren().add(new Teen(child.getId(),
                        child.getLastName(), child.getFirstName(), child.getAge(),
                        child.getCity(), child.getNiceScore(), child.getGiftsPreferences()));
            } else {
                santaClaus.getChildren().add(new YoungAdult(child.getId(),
                        child.getLastName(), child.getFirstName(), child.getAge(),
                        child.getCity(), child.getNiceScore(), child.getGiftsPreferences()));
            }

        }


    }
}
