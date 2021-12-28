package children;

import java.util.ArrayList;

/**
 * Retine lista cu listele de copii din fiecare an
 */
public final class AnnualChildren {
    private ArrayList<Children> annualChildren = new ArrayList<>();

    public ArrayList<Children> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(final ArrayList<Children> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
