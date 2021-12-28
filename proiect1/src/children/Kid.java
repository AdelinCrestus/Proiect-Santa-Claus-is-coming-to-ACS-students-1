package children;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public final class Kid extends ChildStrategy {
    public Kid(final Integer id, final String lastName, final String firstName,
               final Integer age, final Cities city, final Double niceScore,
               final ArrayList<Category> giftsPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences);
    }

    @Override
    public Double average() {
        Double sum = 0.0;
        ArrayList<Double> niceScores = this.getNiceScoreHistory();
        for (Double curent : niceScores) {
            if (curent == null) {
                sum += 0;
            } else {
                sum += curent;
            }
        }
        if (niceScores.size() == 0 && getNiceScore() == 0) {
            this.setAverageScore(0.0);
            return 0.0;
        } else if (niceScores.size() == 0) {
            this.getNiceScoreHistory().add(getNiceScore());
            this.setAverageScore(getNiceScore());
            return  getNiceScore();
        }
        this.setAverageScore(sum / niceScores.size());
        return sum / niceScores.size();
    }
}
