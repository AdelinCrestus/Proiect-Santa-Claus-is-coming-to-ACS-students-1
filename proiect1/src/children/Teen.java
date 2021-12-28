package children;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public final class Teen extends ChildStrategy {
    public Teen(final Integer id, final String lastName, final String firstName,
                final Integer age, final Cities city, final Double niceScore,
                final ArrayList<Category> giftsPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences);
    }

    @Override
    public Double average() {
        double sum = 0.0;
        int num = 0;
        ArrayList<Double> niceScores = this.getNiceScoreHistory();
        for (int i = 0; i < niceScores.size(); i++) {
            if (niceScores.get(i) == null) {
                niceScores.set(i, 0.0);
            }
            sum += niceScores.get(i) * (i + 1);
            num += i + 1;
        }
        if (num == 0 && getNiceScore() == 0) {
            this.setAverageScore(0.0);
            return 0.0;
        } else if (num == 0) {
            this.getNiceScoreHistory().add(getNiceScore());
            this.setAverageScore(getNiceScore());
            return  getNiceScore();
        }
        this.setAverageScore(sum / num);
        return sum / num;
    }
}
