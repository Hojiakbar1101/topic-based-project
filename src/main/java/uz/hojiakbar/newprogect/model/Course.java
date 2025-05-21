package uz.hojiakbar.newprogect.model;

public class Course {
    private Long id;
    private String name;
    private String description; // optional
    private Integer durationInWeeks; // optional

    public Course() {}

    public Course(Long id, String name, String description, Integer durationInWeeks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(Integer durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }
}
