package copy;

import children.Baby;
import children.ChildStrategy;
import children.Kid;
import children.Teen;
import common.Constants;

import java.util.ArrayList;

public final class CopyChildren {
    /**
     * Creeaza o copie a listei de copii.
     */
    public ArrayList<ChildStrategy> copyChildren(final ArrayList<ChildStrategy> children) {
        ArrayList<ChildStrategy> copy = new ArrayList<>();
        for (ChildStrategy child : children) {
            if (child.getAge() < Constants.MINIMUM_AGE_KID) {
                Baby baby = new Baby(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getAge(), child.getCity(),
                        child.getNiceScore(), new ArrayList<>(child.getGiftsPreferences()));
                baby.setNiceScoreHistory(new ArrayList<>(child.getNiceScoreHistory()));
                baby.setAssignedBudget(child.getAssignedBudget());
                baby.setAverageScore(child.getAverageScore());
                copy.add(baby);
            } else if (child.getAge() < Constants.MAXIMUM_AGE_KID) {
                Kid kid = new Kid(child.getId(), child.getLastName(), child.getFirstName(),
                        child.getAge(), child.getCity(), child.getNiceScore(),
                        new ArrayList<>(child.getGiftsPreferences()));
                kid.setNiceScoreHistory(new ArrayList<>(child.getNiceScoreHistory()));
                kid.setAssignedBudget(child.getAssignedBudget());
                kid.setAverageScore(child.getAverageScore());
                copy.add(kid);
            } else if (child.getAge() <= Constants.MAXIMUM_AGE_TEEN) {
                Teen teen = new Teen(child.getId(), child.getLastName(), child.getFirstName(),
                        child.getAge(), child.getCity(), child.getNiceScore(),
                        new ArrayList<>(child.getGiftsPreferences()));
                teen.setNiceScoreHistory(new ArrayList<>(child.getNiceScoreHistory()));
                teen.setAssignedBudget(child.getAssignedBudget());
                teen.setAverageScore(child.getAverageScore());
                copy.add(teen);
            }
        }
        return copy;
    }
}
