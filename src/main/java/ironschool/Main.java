package ironschool;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static List<Student> studentList;
    public static List<Teacher> teacherList;
    public static List<Course> courseList;
    public static void main(String[] args) {
        tempMethodToTestFunctionalities();
        setUpSchool();
       // callMenu();


    }

    // Test method for functionalities to implement later on
    private static void tempMethodToTestFunctionalities() {
        teacherList = new ArrayList<>();
        studentList = new ArrayList<>();
        courseList = new ArrayList<>();
        /*Teacher teacher = new Teacher("Jose", 2000);
        Teacher teacher1 = new Teacher("Josefa", 1000);
        Student student = new Student("St","email@email.com", "en la calle, 69");
        Student student1 = new Student("Stu","email@email.org", "en la calle, 66");
        Course course = new Course("Cursillo",999); Course course1 = new Course("Master",1000);
        teacherList.add(teacher); teacherList.add(teacher1);
        studentList.add(student); studentList.add(student1);
        courseList.add(course); courseList.add(course1);

        System.out.println(teacher.toString());
        System.out.println(teacherList.toString());
        System.out.println(student.toString());
        System.out.println(course.toString());

        Utilities.enrollStudentIntoCourse(student.getStudentId(), course1.getCourseId());

        System.out.println(student);
        System.out.println(course1);*/
    }

    private static void setUpSchool() {

        try {
            System.out.println("Type a name for the school: ");
            String name = sc.nextLine();

            //METODO CREACION DE TEACHERS
            Utilities.createTeacher(teacherList);
            //IMPRIMIMOS LA LISTA DE TEACHERS
            System.out.println(teacherList);

            //METODO CREACION DE CURSOS
            Utilities.createCourses(courseList);
            //IMPRIMIMOS LA LISTA DE CURSOS
            System.out.println(courseList);

            //METODO DE CREACION DE ESTUDIANTES
            Utilities.createStudent(studentList);
            //IMPRIMIMOS LA LISTA DE ESTUDIANTES
            System.out.println(studentList);

        } catch (Exception e) {
            throw new InputMismatchException("Introduce bien los valores");
        }

    }

    private static void callMenu() {
        printMenu();
        int option = sc.nextInt(); //throw new NumberFormatException("test");
        switch (option) {
            case 1: //Enroll
                //Utilities.enrollStudentIntoCourse(...);
                break;
            case 2: //Assign
                //Utilities.assignTeacherIntoCourse(...);
                break;
            case 3: //Courses
                //Utilities.showCourses(...);
                break;
            case 4: //Course
                //Utilities.lookupCourse(...);
                break;
            case 5: //Students
                //Utilities.showStudents(...);
                break;
            case 6: //Student
                //Utilities.lookupStudent(...);
                break;
            case 7: //Teachers
                //Utilities.showTeachers(...);
                break;
            case 8: //Teacher
                //Utilities.lookupTeacher(...);
                break;
            case 9: //Profit
                //Utilities.showProfit(...);
                break;
        }
    }

    private static void printMenu() {
        System.out.println("""
                1) Enroll student to a course
                2) Assign teacher to a course
                3) Show courses
                4) Lookup course
                5) Show students
                6) Lookup student
                7) Show teachers
                8) Lookup teacher
                9) Show profit
                """);
    }

}