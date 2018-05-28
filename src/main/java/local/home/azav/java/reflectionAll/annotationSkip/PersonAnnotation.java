package local.home.azav.java.reflectionAll.annotationSkip;

public class PersonAnnotation {

    private final boolean man;
    private final String name;
    private PersonAnnotation spouse;

    public PersonAnnotation(boolean man, String name) {
        this.man = man;
        this.name = name;
    }

    public boolean isMan() {
        return man;
    }

    @Skip
    public PersonAnnotation getSpouse() {
        return spouse;
    }

    public String getName() {
        return name;
    }

    public String getNameSpouse() {
        return (spouse != null) ? spouse.getName() : "no";
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce
     * (sets spouse = null for husband and wife.
     * Example: if both persons have spouses - then divorce will set 4 spouse to null)
     * and then executes marry().
     *
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person
     * and they are not husband and wife, false otherwise
     */
    public boolean marry(PersonAnnotation person) {
        if (this.man != person.man && this.spouse != person) {
            if (this.spouse != null) {
                this.divorce();
            }
            this.spouse = person;
            person.marry(this);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets spouse = null if spouse is not null
     *
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if (spouse != null) {
            spouse.spouse = null;
            this.spouse = null;
            return true;
        } else {
            return false;
        }
    }
}
