package children;

import enums.Category;
import enums.Cities;
import gifts.Gift;

import java.util.ArrayList;

public abstract class ChildStrategy extends Child {
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    public ChildStrategy() {
    }

    public final Double getAverageScore() {
        return averageScore;
    }

    public final void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public ChildStrategy(final Integer id, final String lastName, final String firstName,
                         final Integer age, final Cities city, final Double niceScore,
                         final ArrayList<Category> giftsPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences);
        niceScoreHistory = new ArrayList<>();
        receivedGifts = new ArrayList<>();
    }

    /**
     * Calculeaza media pentru fiecare copil, in functie de tipul acestuia
     * @return si o returneaza
     */
    public abstract Double average();

    public final ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public final void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public final ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public final void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public final Double getAssignedBudget() {
        return assignedBudget;
    }

    public final void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }
}
