package be.intecbrussel.schoolsout.services;

import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.Grade;
import be.intecbrussel.schoolsout.data.Person;
import be.intecbrussel.schoolsout.data.User;
import be.intecbrussel.schoolsout.repositories.CourseRepository;
import be.intecbrussel.schoolsout.repositories.GradeRepository;
import be.intecbrussel.schoolsout.repositories.UserRepository;
import org.hibernate.tool.schema.SourceType;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GradeService {
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private GradeRepository gradeRepository;
    private UserService userService;

    public GradeService(){
        userRepository = new UserRepository();
        courseRepository = new CourseRepository();
        gradeRepository = new GradeRepository();
        userService =new UserService();

    }

    // Je maakt een nieuwe Grade. Je MOET een bestaande Person en Course meegeven.Jij geeft enkel de Date en gradeValue mee.
    // Zorg ervoor dat de Grade.gradeValue niet groter is dan de Course.maxGradeYouCanGet
    // Als de User al een bestaande Grade heeft voor dit Examen, dan wordt dit examen niet toegevoegd.
    // De datum van de Grade zit standaard by default op vandaag.
    public void createGradeForUserOnCourse(){
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me the user's name");
        String userName =scanner.next();
        System.out.println("Give grade");
        int userGrad = scanner.nextInt();
        System.out.println("Enter course Id");
        int courseId = scanner.nextInt();
        Grade grade = new Grade();
        Course course =courseRepository.getOneById(courseId);
        user = userRepository.getOneById(userName);
        Person person = user.getPerson();
        grade.setPerson(person);
        grade.setCourse(course);
        grade.setDate(LocalDate.now());
        grade.setGradeValue(BigDecimal.valueOf(userGrad));


    }

    //TODO: Delete een Grade
    public void deleteGradeForUserOnCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Just give a course id : ");
        int  input = scanner.nextInt();
        Course course = courseRepository.getOneById(input);

        System.out.println("Give me User. : ");
        String login =scanner.nextLine();
        User user = userRepository.getOneById(login);
        List<Grade> grades = gradeRepository.findAllGradesForUser(user);
                for(Grade grade: grades){
                    if(grade.getCourse().getName().equals(course.getName()));
                    gradeRepository.deleteOne(grade.getId());
                    System.out.println("Grade is deleted");
                }
    }

    //TODO: Je mag enkel de gradeValue veranderen. Zorg ervoor dat de Grade.gradeValue niet groter is dan de Course.maxGradeYouCanGet
    public void updateGradeForUserOnCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me a User.");
        String login = scanner.nextLine();
        System.out.println("Give me a Course Id.");
        int input = scanner.nextInt();
        Course course = courseRepository.getOneById(input);
        User user = userRepository.getOneById(login);
        List<Grade> grades = gradeRepository.findAllGradesForUser(user);
        if(grades.size()>0){
            Grade grade = grades.get(0);
            System.out.println("maximum grade you can give is: "+ course.getMaxGradeYouCanGet());
            System.out.println("How much they have now: "+ grade.getGradeValue());
            BigDecimal bigDecimal ;
            do{
                System.out.println("fill in the new grade.");
                bigDecimal = scanner.nextBigDecimal();
                grade.setGradeValue(bigDecimal);
            }while (bigDecimal.doubleValue()> course.getMaxGradeYouCanGet().doubleValue());

            gradeRepository.updateOne(grade);
        }
    }


    //:Geef een UserName. Gebruik de Person van de User om alle Grades terug te tonen.
    public void findAllGradesForUser(){
        Scanner scanner = new Scanner( System.in);
        System.out.println("Give the name in which you want to know the grade ");
        User user = userRepository.getOneById(scanner.next());
        Person person = user.getPerson();
        Integer id = person.getId();
        List<Grade> allGradeForUser = gradeRepository.findAllGradesForUser(user);
        System.out.println(allGradeForUser);

    }




}
