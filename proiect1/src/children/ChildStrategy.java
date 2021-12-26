package children;

import java.util.ArrayList;

public abstract class ChildStrategy extends Child{
    private ArrayList<Double> niceScores;
    private Double budgetForChild;

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
