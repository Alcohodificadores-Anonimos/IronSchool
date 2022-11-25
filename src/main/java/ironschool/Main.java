package ironschool;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static ironschool.Utilities.enrollStudentIntoCourse;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static List<Course> courseList;
    public static List<Student> studentList;
    public static List<Teacher> teacherList;

    public static void main(String[] args) {

        tempMethodToTestFunctionalities();
        setUpSchool();
        //callMenu();

        Course courseDAM = new Course("DAM", 380);
        Course courseASIX = new Course("ASIX", 250);
        Course courseDAW = new Course("DAW", 380);
        Course courseInfirmary = new Course("Enfermería", 520);
        Course courseCybersecurity = new Course("Ciberseguridad", 720);

        Teacher teacherJaume = new Teacher("Jaume", 2000);
        Teacher teacherAlex = new Teacher("Alex", 2125);
        Teacher teacherJose = new Teacher("Jose", 1985);

        Student studentCristian = new Student("Cristian", "C/ Falsa 123", "cristian@gmail.com");
        Student studentXavi = new Student("Xavi", "Avenida ejemplo 46", "xavi@gmail.com");
        Student studentManu = new Student("Manu", "C/ Caritg 12", "manu@gmail.com");
        Student studentEdu = new Student("Edu", "C/ Extremadura 22", "edu@gmail.com");
        Student studentRaul = new Student("Raul", "C/ Real 456", "raul@gmail.com");

        courseList = new ArrayList<>();
        teacherList = new ArrayList<>();
        studentList = new ArrayList<>();

        //---RAUL---COMPROBACIONES MÉTODO [ENROLL] enrollStudentIntoCourse()---

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

        System.out.println(studentCristian + "\n" + courseDAW);

        enrollStudentIntoCourse(studentCristian.getStudentId(), courseDAW.getCourseId());

        System.out.println(studentCristian + "\n" + courseDAW);

        //---------------------------------------------------------------------

        //---RAUL---COMPROBACIONES MÉTODO [ASSIGN] assignTeacherIntoCourse()---

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

        //---------------------------------------------------------------------

        //---RAUL---COMPROBACIONES MÉTODO [SHOW COURSES] showAllCourses()---

        System.out.println("\nNOMBRES DE TODOS LOS CURSOS:\n");

        Utilities.showAllCourses().forEach(course -> System.out.println(course.getName()));

        //---------------------------------------------------------------------

        //---RAUL---COMPROBACIONES MÉTODO [LOOKUP COURSE] lookupCourse()---

        System.out.println("\nBUSCAMOS CURSO POR ID QUE EXISTE:");

        System.out.println("\n\t" + Utilities.lookupCourse(courseASIX.getCourseId()));

        System.out.println("\nBUSCAMOS CURSO POR ID QUE NO EXISTE:");

        //System.out.println("\n\t" + Utilities.lookupCourse("12345"));

        //---------------------------------------------------------------------

        //---RAUL---COMPROBACIONES MÉTODO [SHOW STUDENTS] showAllStudents()---

        System.out.println("\nNOMBRES DE TODOS LOS ESTUDIANTES:\n");

        Utilities.showAllStudents().forEach(studentElement -> System.out.println(studentElement.getName()));

        //---------------------------------------------------------------------

        //---RAUL---COMPROBACIONES MÉTODO [LOOKUP STUDENT] lookupStudent()---

        System.out.println("\nBUSCAMOS ESTUDIANTE POR ID QUE EXISTE:");

        System.out.println("\n\t" + Utilities.lookupStudent(studentRaul.getStudentId()));

        System.out.println("\nBUSCAMOS ESTUDIANTE POR ID QUE NO EXISTE:");

        //System.out.println("\n\t" + Utilities.lookupStudent("12345"));

        //---------------------------------------------------------------------

        //---RAUL---COMPROBACIONES MÉTODO [SHOW TEACHERS] showAllStudents()---

        System.out.println("\nNOMBRES DE TODOS LOS PROFESORES:\n");

        Utilities.showAllTeachers().forEach(teacherElement -> System.out.println(teacherElement.getName()));

        //---------------------------------------------------------------------

        //---RAUL---COMPROBACIONES MÉTODO [LOOKUP TEACHER] lookupTeacher()---

        System.out.println("\nBUSCAMOS PROFESOR POR ID QUE EXISTE:");

        System.out.println("\n\t" + Utilities.lookupTeacher(teacherJaume.getTeacherId()));

        System.out.println("\nBUSCAMOS PROFESOR POR ID QUE NO EXISTE:");

        //System.out.println("\n\t" + Utilities.lookupTeacher("12345"));

        //---------------------------------------------------------------------

        //---RAUL---COMPROBACIONES MÉTODO [LOOKUP TEACHER] lookupTeacher()---

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

        //---------------------------------------------------------------------

    }

    // Test method for functionalities to implement later on
    private static void tempMethodToTestFunctionalities() {

        courseList = new ArrayList<>();
        teacherList = new ArrayList<>();
        studentList = new ArrayList<>();

      /*  Teacher teacher = new Teacher("Jose", 2000);
        Teacher teacher1 = new Teacher("Josefa", 1000);
        Student student = new Student("St", "email@email.com", "en la calle, 69");
        Student student1 = new Student("Stu", "email@email.org", "en la calle, 66");
        Course course = new Course("Cursillo", 999);
        Course course1 = new Course("Master", 1000);
        teacherList.add(teacher);
        teacherList.add(teacher1);
        studentList.add(student);
        studentList.add(student1);
        courseList.add(course);
        courseList.add(course1);

        System.out.println(teacher.toString());
        System.out.println(teacherList.toString());
        System.out.println(student.toString());
        System.out.println(course.toString());

        enrollStudentIntoCourse(student.getStudentId(), course1.getCourseId());

        System.out.println(student);
        System.out.println(course1);
*/
    }

    private static void setUpSchool() {

        try {
            //--------Falta implementar bucle While(TRUE)
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
            System.err.println(e);

        }
    }




}

