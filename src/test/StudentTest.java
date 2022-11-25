package test;

import ironschool.Course;
import ironschool.Student;
import ironschool.Teacher;
import ironschool.Utilities;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ironschool.Utilities.enrollStudentIntoCourse;

public class StudentTest {

    private Scanner sc = new Scanner(System.in);
    private List<Course> courseList;
    private List<Student> studentList;
    private List<Teacher> teacherList;

    private Course courseDAM;
    private Course courseDAW;
    private Course courseASIX;
    private Course courseInfirmary;
    private Course courseCybersecurity;
    private Teacher teacherJaume;
    private Teacher teacherAlex;
    private Teacher teacherJose;
    private Student studentCristian;
    private Student studentXavi;
    private Student studentManu;
    private Student studentEdu;
    private Student studentRaul;

    // @BeforeAll
    void setUpObjects() {

        courseList = new ArrayList<>();
        studentList = new ArrayList<>();
        teacherList = new ArrayList<>();

        courseDAM = new Course("DAM", 380);
        courseASIX = new Course("ASIX", 250);
        courseDAW = new Course("DAW", 380);
        courseInfirmary = new Course("Enfermería", 520);
        courseCybersecurity = new Course("Ciberseguridad", 720);

        teacherJaume = new Teacher("Jaume", 2000);
        teacherAlex = new Teacher("Alex", 2125);
        teacherJose = new Teacher("Jose", 1985);

        studentCristian = new Student("Cristian", "C/ Falsa 123", "cristian@gmail.com");
        studentXavi = new Student("Xavi", "Avenida ejemplo 46", "xavi@gmail.com");
        studentManu = new Student("Manu", "C/ Caritg 12", "manu@gmail.com");
        studentEdu = new Student("Edu", "C/ Extremadura 22", "edu@gmail.com");
        studentRaul = new Student("Raul", "C/ Real 456", "raul@gmail.com");

        courseList.add(courseDAM);
        courseList.add(courseASIX);
        courseList.add(courseDAW);
        courseList.add(courseInfirmary);
        courseList.add(courseCybersecurity);

        teacherList.add(teacherJaume);
        teacherList.add(teacherAlex);
        teacherList.add(teacherJose);

        studentList.add(studentCristian);
        studentList.add(studentXavi);
        studentList.add(studentManu);
        studentList.add(studentEdu);
        studentList.add(studentRaul);

    }

    // @Test
    void testEnroll() {

        System.out.println("\nALUMNO SIN NINGÚN CURSO ASIGNADO");

        System.out.println(studentCristian + "\n" + courseDAW);

        enrollStudentIntoCourse(studentCristian.getStudentId(), courseDAW.getCourseId());

        System.out.println(studentCristian + "\n" + courseDAW);

        System.out.println("AÑADIMOS UN CURSO CON EL MISMO CURSO ASIGNADO");

        enrollStudentIntoCourse(studentCristian.getStudentId(), courseDAW.getCourseId());

        System.out.println("\nAÑADIMOS UN ALUMNO ASIGNÁNDOLE UN NUEVO CURSO");

        enrollStudentIntoCourse(studentCristian.getStudentId(), courseDAM.getCourseId());

    }

    void testAssign() {

        System.out.println("\nCURSO SIN NINGÚN PROFESOR");

        System.out.println("\n\t" + courseDAM);
        System.out.println("\t" + teacherJaume);

        System.out.println("\nCURSO CON UN PROFESOR");

        Utilities.assignTeacherIntoCourse(teacherJaume.getTeacherId(), courseDAM.getCourseId());

        System.out.println("\n\t" + courseDAM);
        System.out.println("\t" + teacherJaume);

        System.out.println("\nAÑADIMOS UN PROFESOR CON EL MISMO CURSO ASIGNADO");

        Utilities.assignTeacherIntoCourse(teacherJaume.getTeacherId(), courseDAM.getCourseId());

        System.out.println("\n\t" + courseDAM);
        System.out.println("\t" + teacherJaume);

        System.out.println("\nAÑADIMOS UN PROFESOR ASIGNÁNDOLE UN NUEVO CURSO");

        Utilities.assignTeacherIntoCourse(teacherJaume.getTeacherId(), courseASIX.getCourseId());

        System.out.println("\n\t" + courseASIX);
        System.out.println("\t" + teacherJaume);

    }

    void testShowCourses() {

        System.out.println("\nNOMBRES DE TODOS LOS CURSOS:\n");

        Utilities.showAllCourses().forEach(course -> System.out.println(course.getName()));

    }

    void testLookUpCourse() {

        System.out.println("\nBUSCAMOS CURSO POR ID QUE EXISTE:");

        System.out.println("\n\t" + Utilities.lookupCourse(courseASIX.getCourseId()));

        System.out.println("\nBUSCAMOS CURSO POR ID QUE NO EXISTE:");

        System.out.println("\n\t" + Utilities.lookupCourse("12345"));

    }

    void testShowStudents() {

        System.out.println("\nNOMBRES DE TODOS LOS ESTUDIANTES:\n");

        Utilities.showAllStudents().forEach(studentElement -> System.out.println(studentElement.getName()));

    }

    void testLookUpStudent() {

        System.out.println("\nBUSCAMOS ESTUDIANTE POR ID QUE EXISTE:");

        System.out.println("\n\t" + Utilities.lookupStudent(studentRaul.getStudentId()));

        System.out.println("\nBUSCAMOS ESTUDIANTE POR ID QUE NO EXISTE:");

        System.out.println("\n\t" + Utilities.lookupStudent("12345"));

    }

    void testShowTeachers() {

        System.out.println("\nNOMBRES DE TODOS LOS PROFESORES:\n");

        Utilities.showAllTeachers().forEach(teacherElement -> System.out.println(teacherElement.getName()));

    }

    void testLookUpTeacher() {

        System.out.println("\nBUSCAMOS PROFESOR POR ID QUE EXISTE:");

        System.out.println("\n\t" + Utilities.lookupTeacher(teacherJaume.getTeacherId()));

        System.out.println("\nBUSCAMOS PROFESOR POR ID QUE NO EXISTE:");

        System.out.println("\n\t" + Utilities.lookupTeacher("12345"));

    }

    void testShowProfit() {

        System.out.println("\nNOMBRES DE TODOS LOS PROFESORES + SALARIOS:");

        Utilities.showAllTeachers().forEach(teacherElement -> {
            System.out.println("\n\tNombre: " + teacherElement.getName() + " salario: " + teacherElement.getSalary());
        });

        System.out.println("\nNOMBRES DE TODOS LOS CURSOS + BENEFICIO RECAUDADO:");

        Utilities.showAllCourses().forEach(courseElement -> {
            System.out.println("\n\tNombre: " + courseElement.getName() + " beneficio recaudado: " + courseElement.getMoneyEarned());
        });

        System.out.println("\nBENEFICIO DE LOS CURSOS:");

        System.out.println("\n\t" + Utilities.showProfitFromAllCourses(courseList, teacherList));

    }

}
