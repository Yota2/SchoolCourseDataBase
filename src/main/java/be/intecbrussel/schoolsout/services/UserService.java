package be.intecbrussel.schoolsout.services;
import be.intecbrussel.schoolsout.data.*;
import be.intecbrussel.schoolsout.repositories.CourseRepository;
import be.intecbrussel.schoolsout.repositories.GradeRepository;
import be.intecbrussel.schoolsout.repositories.UserRepository;
import be.intecbrussel.schoolsout.repositories.EMFactory;

import javax.management.Query;
import javax.swing.plaf.IconUIResource;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserService {
private  UserRepository  userRepository;
private CourseRepository courseRepository;
private GradeRepository gradeRepository;



    public UserService() {
        userRepository = new UserRepository();
        gradeRepository = new GradeRepository();
        courseRepository = new CourseRepository();
    }

    //Maak een user. Iedere User die je maakt MOET ook een Person hebben
    public void createUser(){
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        User user = new User();

        System.out.println("Give me your userName:");
        String input = scanner.next();
        user.setLogin(input);
        System.out.println("Give me your passWord:");
        input = scanner.next();
        user.setPasswordHash(input);
        System.out.println("Give me your firstName:");
        person.setFirstName(scanner.next());
        System.out.println("Give me your lastName:");
        person.setFamilyName(scanner.next());
        System.out.println("Give me your Gender. 0 = MALE, 1 = FEMALE, 2= NON-BINAIRY, 3 = OTHER:");
        int number = scanner.nextInt();
       for (Gender gender: Gender.values()){
           if (gender.ordinal()==number){
               person.setGender(gender);
           }
       }
       user.setPerson(person);
       userRepository.createOne(user);

    }

    // Delete een user, en delete ook de Person EN de Grades van die Person
    public void deleteUser(){
        System.out.println("Give the user needs to be deleted");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.next();

        User user = userRepository.getOneById(login);
        System.out.println("user: "+ user);
        Queue<Grade>gradeQueue = new LinkedList<>();
        gradeQueue.addAll(gradeRepository.findAllGradesForUser(user));
        while (gradeQueue.size()>0){
    Grade grade = gradeQueue.poll();
            System.out.println("Deletting grade: "+ grade);
    gradeRepository.deleteOne(grade.getId());
        }
        System.out.println("Deleting user: " + user);
        userRepository.deleteOne(login);

    }

    //TODO:Udate de User. Je mag enkel vragen om het volgende te updaten: User.active, Person.firstName en Person.lastName
    public void updateUser(){
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        User user = new User();
        System.out.println("Give your first name");
        String firstName =scanner.next();
        System.out.println("Give your last name");
        String lastName =scanner.next();
        person.setFamilyName(lastName);
        System.out.println("Give user name");
        user.setLogin(scanner.next());
        System.out.println("User is active: ");
        Boolean isActive = scanner.hasNext();
        user.setActive(isActive);
        user.setPerson(person);

    }

    //: Print een User + Person van de database af door een username in te geven.
    public void findOneUserById(){
        System.out.println("Give the user-Name needs to be shown:");
        Scanner scanner = new Scanner(System.in);
        String login =  scanner.nextLine();
        User user = userRepository.getOneById(login);
        System.out.println("User detail: " + user);
    }

    //Print alle users af van de database
    public void findAllUsers(){
        System.out.println("Here are all users:");
        for (User user : userRepository.getAll()){
            System.out.println(user);
        }
    }

    // Toon eerst alle courses. Op basis van de relatie tussen Course, Grade en Person toon je dan alle Persons die die Course hebben gedaan
    public void showAllPeoplePerCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter courseID");
        List<Course> couse = courseRepository.getAll();
        for(Course course:couse){
            System.out.println(course);
        }
        Long login = scanner.nextLong();
        List<User> users = userRepository.getAll();
        Course course = courseRepository.getOneById(login);

        List<Grade> grades = course.getGradesOfCourse();
        List<Person> allPeoplePerCourse = grades.stream().map(Grade::getPerson).collect(Collectors.toList());
for(Person allPeople : allPeoplePerCourse){
    System.out.println(allPeople);
}
       /* Scanner scanner = new Scanner(System.in);
        for (Course course: courseRepository.getAll()) {
            System.out.println(course.getId() + " "+ course.getName());
        }
        System.out.println("choose course ID to see the people enrolled for the course.");
        int courseId = scanner.nextInt();
        Course course = courseRepository.getOneById(courseId);
        for (Grade grade: gradeRepository.get(course)) {
            System.out.println(grade.getPerson());
        }
        */
    }





}
