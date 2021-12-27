package children;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public class Teen extends ChildStrategy{
    public Teen(Integer id, String lastName, String firstName, Integer age, Cities city, Double niceScore, ArrayList<Category> giftsPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences);
    }

    @Override
    public Double average() {
        double sum = 0.0;
        int num = 0;
        ArrayList<Double> niceScores = this.getNiceScoreHistory();
        for(int i = 0 ; i < niceScores.size() ; i++) {
            if(niceScores.get(i) == null) {
                niceScores.set(i,0.0);
            }
            sum += niceScores.get(i) * (i+1);
            num += i+1;
        }
        this.setAverageScore(sum/num);
        return sum/num;
    }
}
