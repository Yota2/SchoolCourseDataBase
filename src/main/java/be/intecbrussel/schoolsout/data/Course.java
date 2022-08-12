package be.intecbrussel.schoolsout.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity 
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    @Column()
    private String name;
    @Column()
    private String description;
    @Column()
    private BigDecimal maxGradeYouCanGet;
    @OneToMany(mappedBy = "course")
    private List<Grade> gradesOfCourse;



    public Course() {
    }

    public Course(String name, String description, BigDecimal maxGradeYouCanGet) {
        this.name = name;
        this.description = description;
        this.maxGradeYouCanGet = maxGradeYouCanGet;
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

    public BigDecimal getMaxGradeYouCanGet() {
        return maxGradeYouCanGet;
    }

    public void setMaxGradeYouCanGet(BigDecimal maxGradeYouCanGet) {
        this.maxGradeYouCanGet = maxGradeYouCanGet;
    }

    public List<Grade> getGradesOfCourse() {
        return gradesOfCourse;
    }

    public void setGradesOfCourse(List<Grade> gradesOfCourse) {
        this.gradesOfCourse = gradesOfCourse;
    }

    public Course(Long id, String name, String description, BigDecimal maxGradeYouCanGet, List<Grade> gradesOfCourse) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxGradeYouCanGet = maxGradeYouCanGet;
        this.gradesOfCourse = gradesOfCourse;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", maxGradeYouCanGet=" + maxGradeYouCanGet +
                '}';
    }
}
