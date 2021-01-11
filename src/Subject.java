/**
 * Class Subject.
 *
 * each subject has a unique id, specialism, duration, description and some methods.
 */
public class Subject {
    private int id;
    private int specialism;
    private int duration;
    private String description;

    /**
     * Constructor.
     *
     * @param id the id of the subject.
     * @param specialism the level of specialism it has.
     * @param duration the duration it has to graduate it.
     * @param description its description.
     */
    public Subject(int id, int specialism, int duration, String description) {
        this.id = id;
        this.specialism = specialism;
        this.duration = duration;
        this.description = description;
    }

    /**
     * Constructor that does not initialize description.
     *
     * @param id the id of the subject.
     * @param specialism the level of specialism it has.
     * @param duration the duration it has to graduate it.
     */
    public Subject(int id, int specialism, int duration) {
        this(id, specialism, duration, null);
    }

    /**
     * ID getter.
     *
     * @return the id of the subject.
     */
    public int getID() {
        return id;
    }

    /**
     * Specialism getter.
     *
     * @return the level of specialism of the subject.
     */
    public int getSpecialism() {
        return specialism;
    }

    /**
     * Duration getter.
     *
     * @return the duration of the subject.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Description getter.
     *
     * @return the description of the subject.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description setter.
     *
     * @param description the description of the subject.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}