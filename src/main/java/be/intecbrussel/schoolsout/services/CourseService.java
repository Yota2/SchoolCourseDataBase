package be.intecbrussel.schoolsout.services;

import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.Grade;
import be.intecbrussel.schoolsout.repositories.CourseRepository;
import be.intecbrussel.schoolsout.repositories.GradeRepository;

import java.math.BigDecimal;
import java.util.*;

public class CourseService {
    private CourseRepository courseRepository;
    private GradeRepository gradeRepository;

    public CourseService() {
        courseRepository = new CourseRepository();
        gradeRepository = new GradeRepository();
    }


//Maak een Course met de constructor


    public void createCourse() {
        Scanner scanner = new Scanner(System.in);
        Course course = new Course();
        System.out.println("Give Name ");
        String dataInput = scanner.nextLine();
        course.setName(dataInput);
        System.out.println("Give the more information about the course");
        dataInput = scanner.next();
        System.out.println("Give the max value  of grade for this course ");
        int maxValue = scanner.nextInt();
        course.setMaxGradeYouCanGet(BigDecimal.valueOf(maxValue));
        courseRepository.createOne(course);
    }

    //TODO: Delete een course, en delete alle Grades van die Course
    public void deleteCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the course id that need's to be deleted");
        int inputID = scanner.nextInt();
        Course course = courseRepository.getOneById(inputID);
        Queue<Grade> gradeQueue = new LinkedList<>();
        while (gradeQueue.size() > 0) {
            Grade grade = gradeQueue.poll();
            gradeRepository.deleteOne(grade.getId());
        }
        courseRepository.deleteOne(inputID);
    }

    //TODO:Update een Course. Je mag enkel de name, description en maxGradeYouCanGet editten
    public void updateCourse() {
        courseRepository.getAll();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give a Course Id.. ");
        int inputData = scanner.nextInt();
        Course course = courseRepository.getOneById(inputData);
        System.out.println("Do you like to update some course ? If so pls press Y ");
        String userWants = scanner.next();

        if (userWants.toUpperCase(Locale.ROOT).equals("Y")) {
            System.out.println("Give the name of the course");
            String courseName = scanner.next();
            course.setName(courseName);
        } else {
            System.out.println("No update sorry");
        }
        System.out.println("Would you like to give The description of the course if so pls write -Y-");
        String userDescription = scanner.next();
        if (userDescription.toUpperCase(Locale.ROOT).equals("Y")) {
            System.out.println("Give the description of the course");
            String descriptionCourse = scanner.next();
            course.setDescription(descriptionCourse);
        } else {
            System.out.println("Description of the course is not updated");
            }
            System.out.println("Do you want to upgrade the score of the course  ");
            String scoreUpgrade = scanner.next();

            if (scoreUpgrade.toUpperCase(Locale.ROOT).equals("Y")) {
                System.out.println("Give the new score value");
                int scoreValue = scanner.nextInt();
                course.setMaxGradeYouCanGet(BigDecimal.valueOf(scoreValue));
            } else {
                System.out.println("No change is made regarding the score");
            }
            courseRepository.updateOne(course);
            System.out.println("Course has been updated");
        }



    //TODO:Toon een course op basis van Id
    public void findOneCourseById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give course Id");
        int login =scanner.nextInt();
        Course course = new Course();
        course = courseRepository.getOneById(login);
        System.out.println(course);
    }

  //TODO: Toon alle Courses
    public void findAllCourses(){
        System.out.println("All courses are shown :");
        for (Course course: courseRepository.getAll()) {
            System.out.println(course);
        }
    }


    // Print alle Grades van een Course (hint: gettermethode)
    public void findAllGradesFromCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give the course id inorder to see the grades");
        int  id = scanner.nextInt();
        CourseRepository courseRep = new CourseRepository();
        Course course = courseRep.getOneById(id);
        List<Grade> grade = course.getGradesOfCourse();
        for(Grade g: grade){
            System.out.println(g);
        }
    }



}





