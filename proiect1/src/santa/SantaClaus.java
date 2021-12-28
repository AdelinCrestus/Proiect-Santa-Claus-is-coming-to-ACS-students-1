package santa;

import children.ChildStrategy;
import children.Child;
import children.ChildFactory;
import children.Teen;
import children.Kid;
import children.ChildUpdate;
import children.AnnualChildren;
import children.Children;
import common.Constants;
import comparators.ComparatorGifts;
import copy.CopyChildren;
import copy.CopyGifts;
import data.Input;
import enums.Category;
import gifts.Gift;

import java.util.ArrayList;

public final class SantaClaus {
    private static SantaClaus santaClaus;
    private Double budget;
    private ArrayList<Gift> giftList;
    private ArrayList<ChildStrategy> children;
    private Double budgetUnit;

    private SantaClaus() { //singleton
    }

    /**
     * Mos Craciun este unul singur, deci am folosit singleton.
     * Si iau instanta cu getInstance
     * @return returneaza instanta
     */

    public static SantaClaus getInstance() {
        if (santaClaus == null) {
            santaClaus = new SantaClaus();

        }
        santaClaus.giftList = new ArrayList<>();
        santaClaus.children = new ArrayList<>();
        return santaClaus;
    }

    /**
     * Mos Craciun ofera cadoul in ordinea crescatoare preturilor.
     * Sorteaza crescator lista de cadouri dupa pret
     */
    public void sortGiftList() {
        giftList.sort(new ComparatorGifts());
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(final Double budget) {
        this.budget = budget;
    }

    public ArrayList<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(final ArrayList<Gift> giftList) {
        this.giftList = giftList;
    }

    public ArrayList<ChildStrategy> getChildren() {
        return children;
    }

    public void setChildren(final ArrayList<ChildStrategy> children) {
        this.children = children;
    }

    public Double getBudgetUnit() {
        return budgetUnit;
    }

    public void setBudgetUnit(final Double budgetUnit) {
        this.budgetUnit = budgetUnit;
    }

    /**
     * Seteaza datele initiale in memorie
     * @param input - datele primite
     */
    public void set(final Input input) {
        santaClaus.setBudget(input.getSantaBudget());
        santaClaus.setGiftList(input.getInitialData().getSantaGiftsList());
        ArrayList<Child> childArrayList = input.getInitialData().getChildren();
        for (Child child : childArrayList) {
            santaClaus.getChildren().add(ChildFactory.createChild(child));
        }
    }

    /**
     * Calculeaza BudgetUnit dupa pasinii dati in cerinta
     * @return
     */
    public Double calculateBudgetUnit() {
        double sum = 0.0;
        ArrayList<ChildStrategy> childStrategyArrayList = getChildren();
        for (ChildStrategy child : childStrategyArrayList) {
            sum += child.average();
        }
        setBudgetUnit(getBudget() / sum);
        return getBudget() / sum;
    }

    /**
     * Calculeaza bugetul pentru fiecare copil si seteaza campul corespunzator
     */

    public void setBudgetForEachChild() {
        ArrayList<ChildStrategy> childStrategyArrayList = getChildren();
        calculateBudgetUnit();
        for (ChildStrategy child : childStrategyArrayList) {
            child.setAssignedBudget(child.average() * getBudgetUnit());
        }
    }

    /**
     * Actiunea prin care Mos Craciun da cadoul gift copilului child si il sterge din lista mosului
     * @param child - copilul care va primi cadoul
     * @param gift - cadoul ce va fi oferit
     * @param giftsSanta - lista mosului cu cadouri
     */

    public void giveGift(final ChildStrategy child, final Gift gift,
                         final ArrayList<Gift> giftsSanta) {
        ArrayList<Gift> gifts = child.getReceivedGifts();
        gifts.add(gift);
        child.setReceivedGifts(gifts);
        giftsSanta.remove(gift);
    }

    /**
     * Gaseste copilul cu id-ul dat ca parametru in lista mosului si il returneaza
     * @param id - id-ul pe care il cautam
     * @return returneaza copilul
     */
    public ChildStrategy findChild(final int id) {
        for (ChildStrategy child : children) {
            if (child.getId().compareTo(id) == 0) {
                return child;
            }
        }
        return null;
    }

    /**
     * Actualizeaza datele pentru noul an in care Mosul ofera cadouri
     * @param input datele de intrare
     * @param index anul curent
     */

    public void update(final Input input, final int index) {
        for (int i = 0; i < children.size(); i++) {
            ChildStrategy child = children.get(i);
            int oldAge = child.getAge();
            child.setAge(oldAge + 1);
            if (oldAge == Constants.MAXIMUM_AGE_KID - 1) {
                children.remove(child);
                Teen teen = new Teen(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getAge(), child.getCity(),
                        child.getNiceScore(), child.getGiftsPreferences());
                teen.setNiceScoreHistory(child.getNiceScoreHistory());
                teen.setReceivedGifts(child.getReceivedGifts());
                teen.setAssignedBudget(child.getAssignedBudget());
                children.add(i, teen);
            } else if (oldAge == Constants.MINIMUM_AGE_KID - 1) {
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
        children.removeIf(child -> child.getAge() > Constants.MAXIMUM_AGE_TEEN);
        budget = input.getAnnualChanges().get(index).getNewSantaBudget();
        ArrayList<Gift> newGifts = input.getAnnualChanges().get(index).getNewGifts();
        if (newGifts != null) {
            giftList.addAll(newGifts);
        }
        ArrayList<Child> newChildren = input.getAnnualChanges().get(index).getNewChildren();
        if (newChildren != null) {
            for (Child child : newChildren) {
                children.add(ChildFactory.createChild(child));
            }
            ArrayList<ChildUpdate> childrenUpdates = input.getAnnualChanges().get(index).
                    getChildrenUpdates();
            for (ChildUpdate childUpdate : childrenUpdates) {
                ChildStrategy child = findChild(childUpdate.getId());
                if (child != null) {
                    ArrayList<Category> giftsPreferences = childUpdate.getGiftsPreferences();
                    if (giftsPreferences != null) {
                        for (int i = giftsPreferences.size() - 1; i >= 0; i--) {
                            for (int j = 0; j < child.getGiftsPreferences().size(); j++) {
                                if (giftsPreferences.get(i).toString().compareTo(
                                        child.getGiftsPreferences().get(j).toString()) == 0) {
                                    child.getGiftsPreferences().remove(j);
                                    break;
                                }
                            }
                            child.getGiftsPreferences().add(0, giftsPreferences.get(i));
                        }
                    }
                    if (childUpdate.getNiceScore() != null) {
                        child.getNiceScoreHistory().add(childUpdate.getNiceScore());
                    }
                }
            }
        }
    }
    /**
     * Flow-ul simularii
     * Actiunea mosului de a da cadouri an de an copiilor
     * @param input datele de intrare oferite in json
     * @return Lista cu liste de copii pentru fiecare an
     */
    public AnnualChildren santaAction(final Input input) {
        set(input);
        AnnualChildren annualChildren = new AnnualChildren();
        for (int i = 0; i <= input.getNumberOfYears(); i++) {
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
                        if (category.equals(gift.getCategory())
                                && (totalPrice + gift.getPrice() <= child.getAssignedBudget())) {
                            totalPrice += gift.getPrice();
                            giveGift(child, gift, gifts);
                            break;
                        }
                    }
                }
            }
            Children children2 = new Children();
            for (ChildStrategy child : childrenList) {
                children2.getChildren().add(child);
            }
            int index = annualChildren.getAnnualChildren().size();
            annualChildren.getAnnualChildren().add(index, children2);
            if (i < input.getNumberOfYears()) {
                update(input, i);
            }
        }
        return annualChildren;
    }
}
