package children;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public abstract class ChildStrategy extends Child{
    private ArrayList<Double> niceScores;
    private Double budgetForChild;

    public ChildStrategy() {
    }

    public ChildStrategy(Integer id, String lastName, String firstName, Integer age, Cities city, Double niceScore, ArrayList<Category> giftsPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences);
    }

    public abstract Double average();

    public ArrayList<Double> getNiceScores() {
        return niceScores;
    }

    public void setNiceScores(ArrayList<Double> niceScores) {
        this.niceScores = niceScores;
    }

    public Double getBudgetForChild() {
        return budgetForChild;
    }

    public void setBudgetForChild(Double budgetForChild) {
        this.budgetForChild = budgetForChild;
    }
}
