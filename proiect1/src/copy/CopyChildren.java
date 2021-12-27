package copy;

import children.Baby;
import children.ChildStrategy;
import children.Kid;
import children.Teen;

import java.util.ArrayList;

public class CopyChildren {
    public ArrayList<ChildStrategy> copyChildren(ArrayList<ChildStrategy> children) {
        ArrayList<ChildStrategy> copy = new ArrayList<>();
        for(ChildStrategy child : children) {
            if( child.getAge() < 5) {
                Baby baby = new Baby(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getAge(), child.getCity(),
                        child.getNiceScore(), child.getGiftsPreferences());
                baby.setNiceScoreHistory(child.getNiceScoreHistory());
                baby.setAssignedBudget(child.getAssignedBudget());
                baby.setAverageScore(child.getAverageScore());
                copy.add(baby);
            } else if (child.getAge() < 12 && child.getAge() >= 5) {
                Kid kid = new Kid(child.getId(), child.getLastName(), child.getFirstName(),
                        child.getAge(), child.getCity(), child.getNiceScore(), child.getGiftsPreferences());
                kid.setNiceScoreHistory(child.getNiceScoreHistory());
                kid.setAssignedBudget(child.getAssignedBudget());
                kid.setAverageScore(child.getAverageScore());
                copy.add(kid);
            } else if (child.getAge() >= 12 && child.getAge() <= 18) {
                Teen teen = new Teen(child.getId(), child.getLastName(), child.getFirstName(),
                        child.getAge(), child.getCity(), child.getNiceScore(), child.getGiftsPreferences());
                teen.setNiceScoreHistory(child.getNiceScoreHistory());
                teen.setAssignedBudget(child.getAssignedBudget());
                teen.setAverageScore(child.getAverageScore());
                copy.add(teen);
            }
        }
        return copy;
    }
}
