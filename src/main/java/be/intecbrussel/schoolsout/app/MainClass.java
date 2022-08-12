package be.intecbrussel.schoolsout.app;




import be.intecbrussel.schoolsout.repositories.EMFactory;
import be.intecbrussel.schoolsout.services.CourseService;
import be.intecbrussel.schoolsout.services.GradeService;
import be.intecbrussel.schoolsout.services.UserService;

import java.util.Locale;
import java.util.Scanner;
public class MainClass {
    private static int choiceOne= 9;
    private static int choiceTwo= 9;
    private static boolean continueThis= true;


    public static void main(String[] args) {

      EMFactory.getEmf();
            while (continueThis){
                getChoice();

                if(choiceOne!=0){

                    switch (choiceOne){
                        case 1:choiceUsers();break;
                        case 2:choiceCourses();break;
                        case 3:choiceGrades();break;
                    }

                }

                choices();

            }


    }

    public static void getChoice(){
        Scanner scanner = new Scanner(System.in);

        while (choiceOne==9) {
            System.out.println("What do you want to look at? \n1: Users \n2: Courses \n3: Grades \n0: End");
            choiceOne = scanner.nextInt();
            if(choiceOne==0)break;
            if(choiceOne<1||choiceOne>3){
                choiceOne = 9;
                System.out.println("Invalid choice.");
            }
        }
    }
    private static void choiceUsers()  {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        System.out.println("User Part");
        while (choiceTwo==9) {
            System.out.println("What do you want to look at? \n1: See All Users \n2: See One User\n3: Add One User\n4: Edit One User\n5: Delete One User\n6: See all Users per Course\n 0: End");
            choiceTwo = scanner.nextInt();
            if (choiceTwo==0)break;
            if(choiceTwo<1||choiceTwo>6){
                choiceTwo = 9;
                System.out.println("Invalid choice.");
            }
        }
            switch (choiceTwo){
                case 1:userService.findAllUsers();break;//LEVEL 1
                case 2:userService.findOneUserById();break;//LEVEL 3
                case 3:userService.createUser();break;//LEVEL 4
                case 4:userService.updateUser();break;//LEVEL 2
                case 5:userService.deleteUser();break;//LEVEL 2
                case 6:userService.showAllPeoplePerCourse();break;//LEVEL 4
            }
            System.out.println("We did a User thing!");


    }

    private static void choiceCourses()  {
        Scanner scanner = new Scanner(System.in);
        CourseService courseService = new CourseService();
        System.out.println("Course Part");
        while (choiceTwo==9) {
            System.out.println("What do you want to look at? \n1: See All Courses\n2: See One Course\n3: Add One Course\n4: Edit One Course\n5: Delete One Course\n0: End");
            choiceTwo = scanner.nextInt();
            if (choiceTwo==0)break;
            if(choiceTwo<1||choiceTwo>5){
                choiceTwo = 9;
                System.out.println("Invalid choice.");
            }
        }

        switch (choiceTwo){
            case 1:courseService.findAllCourses();break;//LEVEL 1
            case 2:courseService.findOneCourseById();break;//LEVEL 1
            case 3:courseService.createCourse();break;//LEVEL 1
            case 4:courseService.updateCourse();break;//LEVEL 2
            case 5:courseService.deleteCourse();break;//LEVEL 2

        }
        System.out.println("We did a Course thing!");
    }

    private static void choiceGrades()  {
        Scanner scanner = new Scanner(System.in);
        GradeService gradeService = new GradeService();
        CourseService courseService = new CourseService();
        System.out.println("Grade Part");
        while (choiceTwo==9) {
            System.out.println("What do you want to look at? \n1: See All Grades Per Course \n2: See All Grades Per User \n3: Add One Grade\n4: Edit One Grade\n5: Delete One Grade\n0: End");
            choiceTwo = scanner.nextInt();
            if (choiceTwo==0)break;
            if(choiceTwo<1||choiceTwo>5){
                choiceTwo = 9;
                System.out.println("Invalid choice.");
            }
        }

        switch (choiceTwo){
            case 1:courseService.findAllGradesFromCourse();break;//LEVEL 2
            case 2:gradeService.findAllGradesForUser();break;//LEVEL 3
            case 3:gradeService.createGradeForUserOnCourse();break;//LEVEL 4
            case 4:gradeService.updateGradeForUserOnCourse();break;//LEVEL 2
            case 5:gradeService.deleteGradeForUserOnCourse();break;//LEVEL 1
        }
        System.out.println("We did a Grade thing!");


    }



    private static void choices()  {
        Scanner scanner = new Scanner(System.in);
        choiceOne =9;
        choiceTwo =9;
        boolean goodAnswer;
        do {
            System.out.println("Do you want to Try again? Y/N");
            String answer = scanner.next();
            if (answer.toUpperCase(Locale.ROOT).equals("N")){
                System.out.println("Bye!");
                continueThis = false;
                break;
            }
            if (!answer.toUpperCase(Locale.ROOT).equals("Y")) {
                goodAnswer = false;
                System.out.println(answer+ " is not a good answer.");
            }
            else goodAnswer = true;
        }while (!goodAnswer);

    }

    private static void addCourse() {

    }

}
