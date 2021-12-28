package copy;

import children.ChildStrategy;
import children.ChildFactory;
import common.Constants;

import java.util.ArrayList;

public final class CopyChildren {
    /**
     * Creeaza o copie a listei de copii.
     */
    public ArrayList<ChildStrategy> copyChildren(final ArrayList<ChildStrategy> children) {
        ArrayList<ChildStrategy> copy = new ArrayList<>();
        for (ChildStrategy child : children) {
            ChildStrategy childStrategy = ChildFactory.createChild(child);
            childStrategy.setNiceScoreHistory(new ArrayList<>(child.getNiceScoreHistory()));
            childStrategy.setAssignedBudget(child.getAssignedBudget());
            childStrategy.setAverageScore(child.getAverageScore());
            childStrategy.setGiftsPreferences(new ArrayList<>(child.getGiftsPreferences()));
            if (childStrategy.getAge() <= Constants.MAXIMUM_AGE_TEEN) {
                copy.add(childStrategy);
            }
        }
        return copy;
    }
}
