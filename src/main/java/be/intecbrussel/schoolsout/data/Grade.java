package be.intecbrussel.schoolsout.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Grade {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Course course;
    private BigDecimal gradeValue;
    private LocalDate date;

    public Grade() {
    }

    public Grade(Person person, Course course, BigDecimal gradeValue, LocalDate date) {
        this.person = person;
        this.course = course;
        this.gradeValue = gradeValue;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public BigDecimal getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(BigDecimal gradeValue) {
        this.gradeValue = gradeValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", person=" + person +
                ", course=" + course +
                ", gradeValue=" + gradeValue +
                ", date=" + date +
                '}';
    }
}
