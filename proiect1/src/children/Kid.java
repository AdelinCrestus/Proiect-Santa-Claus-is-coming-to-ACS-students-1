package children;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public class Kid extends ChildStrategy{
    public Kid(Integer id, String lastName, String firstName, Integer age, Cities city, Double niceScore, ArrayList<Category> giftsPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences);
    }

    @Override
    public Double average() {
        Double sum = 0.0;
        ArrayList<Double> niceScores = this.getNiceScoreHistory();
        for(Double curent : niceScores) {
            if (curent == null) {
                sum += 0;
            } else {
                sum += curent;
            }
        }
        this.setAverageScore(sum/niceScores.size());
        return sum/niceScores.size();
    }
}
