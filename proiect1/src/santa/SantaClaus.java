package santa;

import children.*;
import comparators.ComparatorGifts;
import copy.CopyChildren;
import copy.CopyGifts;
import data.Input;
import enums.Category;
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
        santaClaus.giftList = new ArrayList<>();
        santaClaus.children = new ArrayList<>();
        return santaClaus;
    }

    public void sortGiftList() {
        giftList.sort(new ComparatorGifts());
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

    public Double getBudgetUnit() {
        return budgetUnit;
    }

    public void setBudgetUnit(Double budgetUnit) {
        this.budgetUnit = budgetUnit;
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
            santaClaus.getChildren().get(getChildren().size() - 1).getNiceScoreHistory().add(child.getNiceScore());

        }
    }

    public Double calculateBudgetUnit() {
        double sum = 0.0;
        ArrayList<ChildStrategy> children = getChildren();
        for( ChildStrategy child : children) {
            sum += child.average();
        }
        setBudgetUnit(getBudget()/sum);
        return getBudget()/sum;
    }

    public void setBudgetForEachChild() {
        ArrayList<ChildStrategy> children = getChildren();
        calculateBudgetUnit();
        for( ChildStrategy child : children) {
            child.setAssignedBudget(child.average() * getBudgetUnit());
        }
    }

    public void giveGift(ChildStrategy child, Gift gift,ArrayList<Gift> giftsSanta) {
        ArrayList<Gift> gifts = child.getReceivedGifts();
        gifts.add(gift);
        child.setReceivedGifts(gifts);
        giftsSanta.remove(gift);
    }

    public ChildStrategy findChild(int id) {
        for(ChildStrategy child : children) {
            if (child.getId().compareTo(id) == 0) {
                return child;
            }
        }
        return null;
    }

    public void update(Input input, int index) {
        for(int i = 0 ; i < children.size(); i++) {
            ChildStrategy child = children.get(i);
            int oldAge = child.getAge();
            child.setAge(oldAge + 1);
            if(oldAge == 11) {
                children.remove(child);
                Teen teen = new Teen(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getAge(), child.getCity(),
                        child.getNiceScore(), child.getGiftsPreferences());
                teen.setNiceScoreHistory(child.getNiceScoreHistory());
                teen.setReceivedGifts(child.getReceivedGifts());
                teen.setAssignedBudget(child.getAssignedBudget());
                children.add(i, teen );
            } else if ( oldAge == 4) {
                children.remove(child);
                Kid kid = new Kid(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getAge(), child.getCity(),
                        child.getNiceScore(), child.getGiftsPreferences());
                kid.setNiceScoreHistory(child.getNiceScoreHistory());
                kid.setAssignedBudget(child.getAssignedBudget());
                kid.setReceivedGifts(child.getReceivedGifts());
                children.add(i, kid);
            }

        }
        children.removeIf(child -> child.getAge() > 18);
        budget = input.getAnnualChanges().get(index).getNewSantaBudget();
        ArrayList<Gift> newGifts = input.getAnnualChanges().get(index).getNewGifts();
        if( newGifts != null) {
            giftList.addAll(newGifts);
        }
        ArrayList<Child> newChildren = input.getAnnualChanges().get(index).getNewChildren();
        if(newChildren != null) {
            for(Child child : newChildren) {
                if( child.getAge() < 5) {
                    children.add(new Baby(child.getId(), child.getLastName(),
                            child.getFirstName(), child.getAge(), child.getCity(),
                            child.getNiceScore(), child.getGiftsPreferences()));
                } else if (child.getAge() < 12 && child.getAge() >= 5) {
                    children.add(new Kid(child.getId(), child.getLastName(), child.getFirstName(),
                            child.getAge(), child.getCity(), child.getNiceScore(), child.getGiftsPreferences()));
                } else if (child.getAge() >= 12 && child.getAge() <= 18) {
                    children.add(new Teen(child.getId(), child.getLastName(), child.getFirstName(),
                            child.getAge(), child.getCity(), child.getNiceScore(), child.getGiftsPreferences()));
                }
            }
        }
        ArrayList<ChildUpdate> childrenUpdates = input.getAnnualChanges().get(index).getChildrenUpdates();
        for(ChildUpdate childUpdate : childrenUpdates) {
            ChildStrategy child = findChild(childUpdate.getId());
            if(child != null) {
                ArrayList<Category> giftsPreferences = childUpdate.getGiftsPreferences();
                if (giftsPreferences != null) {
                    for (int i = giftsPreferences.size() - 1; i >= 0; i--) {
                        child.getGiftsPreferences().add(0, giftsPreferences.get(i));
                    }
                }
                child.getNiceScoreHistory().add(childUpdate.getNiceScore());
            }
        }

    }

    public AnnualChildren santaAction(Input input) {
        set(input);
        AnnualChildren annualChildren = new AnnualChildren();
        for(int i = 0 ; i <= input.getNumberOfYears() ; i++ ) {
            setBudgetForEachChild();
            sortGiftList();
            ArrayList<ChildStrategy> childrenList = new CopyChildren().copyChildren(children);
            for (ChildStrategy child : childrenList) {
                Double totalPrice = 0.0;
                ArrayList<Gift> gifts = new CopyGifts().copyGiftList(giftList);
                for (Category category : child.getGiftsPreferences()) {
                    if (totalPrice >= child.getAssignedBudget()) {
                        break;
                    }

                    for (Gift gift : gifts) {
                        if (category.equals(gift.getCategory()) && (totalPrice + gift.getPrice() <= child.getAssignedBudget())) {
                            totalPrice += gift.getPrice();
                            giveGift(child, gift,gifts);
                            break;
                        }
                    }
                }
            }
            Children children2 = new Children();
            for(ChildStrategy child : childrenList) {
                children2.getChildren().add(child);
            }
            int index = annualChildren.getAnnualChildren().size();
            annualChildren.getAnnualChildren().add(index, children2);
            if( i < input.getNumberOfYears()) {
                update(input, i);
            }
        }
        return annualChildren;
    }
}
