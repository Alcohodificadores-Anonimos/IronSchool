package ironschool;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Utilities {

    //CLASE DONDE SE CREARAN TODOS LOS MÉTODOS DE CREACIÓN Y GESTIÓN

    private static Scanner scanner;

    public static String generarIdRandom() {
        return UUID.randomUUID().toString();
    }

    public static void commands(String command) {

        //command -> ENROLL 1 2
        //command2 -> ENROLL
        //studentID -> 1
        //courseID -> 2

        String command2 = command.split(" ")[0].toUpperCase();
        String studentID = command.split(" ")[1].toUpperCase();
        String courseID = command.split(" ")[2].toUpperCase();

        switch (command2) {

            case "ENROLL":

                enrollStudentIntoCourse(studentID, courseID);

                break;

            case "ASSIGN":

                break;

        }


    }

    //Método para inscribir un alumno a un curso mediante sus IDs
    public static void enrollStudentIntoCourse(String studentID, String courseID) {

        scanner = new Scanner(System.in);

        Student student = null;
        Course course = null;

        int option;

        //Buscamos si el ID del estudiante introducido existe en la lista de estudiantes
        for (Student studentElement : Main.studentList) {

            if (studentElement.getStudentId().equals(studentID)) {
                student = studentElement;
                break;
            }

        }

        //Si el alumno no existe o el ID está mal escrito, lanzamos una exception
        if (student == null) {
            System.err.println("No existe el alumno con ID (" + studentID + ") o está mal escrito, volviendo al menú principal.");
            return;
        }

        //Buscamos si el ID del curso introducido existe en la lista de cursos
        for (Course courseElement : Main.courseList) {

            if (courseElement.getCourseId().equals(courseID)) {
                course = courseElement;
                break;
            }

        }

        //Si el curso no existe o el ID está mal escrito, lanzamos una exception
        if (course == null) {
            System.err.println("No existe el curso con ID (" + courseID + ") o está mal escrito, volviendo al menú principal.");
            return;
        }

        //Comprobamos que el estudiante no esté ya inscrito en un curso
        if (student.getCourse() != null) {

            //Si el curso que estemos añadiendo es el mismo que ya tiene asignado, saltamos el proceso y no lo cobramos
            if (student.getCourse().getName().equals(course.getName())) {
                System.out.println("\n\t¡ATENCIÓN!\n\tEl estudiante ya estaba inscrito en el curso (" + course.getName() +
                        "), volviendo al menú.");
                return;
            }

            //Si el alumno ya estaba asignado a un curso, preguntamos al usuario que quiere hacer (1. Sobreescribir, 2. No)
            System.out.println("\n\t¡ATENCIÓN!\n\tEl estudiante está inscrito en un curso, quieres sobreescribir" +
                    " el curso actual (" + student.getCourse().getName() + ") por el curso nuevo (" + course.getName()
                    + ")? (1.Si | 2.No)");

            //Validamos que la opción introducida sea correcta
            while (true) {

                if (!scanner.hasNextInt()) {

                    System.out.println("Introduce un valor válido! (1.Si | 2.No)");
                    scanner.next();

                } else {

                    option = scanner.nextInt();

                    if (option == 1 || option == 2) break;
                    else System.out.println("Opción incorrecta! (1.Si | 2.No)");

                }

            }

            //Si es 1, actualizamos el curso, si no (opción 2), no lo actualizamos
            if (option == 1) {

                //Le asignamos el nuevo curso al estudiante
                student.setCourse(course);

                //Actualizamos el dinero que tenga del curso sumándole lo que ha costado el curso
                course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());

                System.out.println("\n\tCurso actualizado! Nombre del nuevo curso: (" + course.getName() + ") " +
                        "Volviendo al menú principal.");

            } else {

                System.out.println("\n\tCurso no actualizado. Volviendo al menú principal.");

            }

            return;

        }

        //Le asignamos el curso al estudiante
        student.setCourse(course);

        //Actualizamos el dinero que tenga del curso sumándole lo que ha costado el curso
        course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());

        System.out.println("\n\tNuevo estudiante (" + student.getName() + ") añadido al curso (" + course.getName() + "). " +
                "Volviendo al menú principal");

    }

    //Método para asignar un profesor a un curso mediante sus IDs
    public static void assignTeacherIntoCourse(String teacherID, String courseID) {

        scanner = new Scanner(System.in);

        Teacher teacher = null;
        Course course = null;

        //Buscamos si el ID del profesor introducido existe en la lista de profesores
        for (Teacher teacherElement : Main.teacherList) {

            if (teacherElement.getTeacherId().equals(teacherID)) {
                teacher = teacherElement;
                break;
            }

        }

        //Si el profesor no existe o el ID está mal escrito, lanzamos una exception
        if (teacher == null) {
            System.err.println("No existe el profesor con ID (" + teacherID + ") o está mal escrito, volviendo al menú principal.");
            return;
        }

        //Buscamos si el ID del curso introducido existe en la lista de cursos
        for (Course courseElement : Main.courseList) {

            if (courseElement.getCourseId().equals(courseID)) {
                course = courseElement;
                break;
            }

        }

        //Si el curso no existe o el ID está mal escrito, lanzamos una exception
        if (course == null) {
            System.err.println("No existe el curso con ID (" + courseID + ") o está mal escrito, volviendo al menú principal.");
            return;
        }

        //Comprobamos que el profesor no esté ya asignado en un curso
        if (course.getTeacher() != null) {

            //Si el curso que estemos asignando es el mismo que ya tiene asignado, saltamos el proceso
            if (course.getTeacher().getName().equals(teacher.getName())) {
                System.out.println("\n\t¡ATENCIÓN!\n\tEl profesor ya estaba asignado en el curso (" + course.getName() +
                        "), volviendo al menú.");
                return;
            }

        }

        //Le asignamos el profesor al curso
        course.setTeacher(teacher);

        System.out.println("\n\tNuevo profesor (" + teacher.getName() + ") asignado al curso (" + course.getName() + ")." +
                "Volviendo al menú principal.");

    }

    //Método para enseñar todos los cursos
    public static List<Course> showAllCourses() {
        return Main.courseList;
    }

    //Método para enseñar toda la información de un curso mediante su ID
    public static String lookupCourse(String courseID) {

        Course course = null;

        //Buscamos si el ID del curso introducido existe en la lista de cursos
        for (Course courseElement : Main.courseList) {

            if (courseElement.getCourseId().equals(courseID)) {
                course = courseElement;
                break;
            }

        }

        if (course == null) {
            System.err.println("No existe el curso con ID (" + courseID + ") o está mal escrito. Volviendo al menú principal.");
            return null;
        }

        return course.toString();

    }

    //Método para enseñar todos los estudiantes
    public static List<Student> showAllStudents() {
        return Main.studentList;
    }

    //Método para enseñar toda la información de un estudiante mediante su ID
    public static String lookupStudent(String studentID) {

        Student student = null;

        //Buscamos si el ID del curso introducido existe en la lista de cursos
        for (Student studentElement : Main.studentList) {

            if (studentElement.getStudentId().equals(studentID)) {
                student = studentElement;
                break;
            }

        }

        if (student == null) {
            System.err.println("No existe el estudiante con ID (" + studentID + ") o está mal escrito. Volviendo al menú principal.");
            return null;
        }

        return student.toString();

    }

    //Método para enseñar todos los profesores
    public static List<Teacher> showAllTeachers() {
        return Main.teacherList;
    }


    //Método para enseñar toda la información de un profesor mediante su ID
    public static String lookupTeacher(String teacherID) {

        Teacher teacher = null;

        //Buscamos si el ID del curso introducido existe en la lista de cursos
        for (Teacher teacherElement : Main.teacherList) {

            if (teacherElement.getTeacherId().equals(teacherID)) {
                teacher = teacherElement;
                break;
            }

        }

        if (teacher == null) {
            System.err.println("No existe el profesor con ID (" + teacherID + ") o está mal escrito. Volviendo al menú principal.");
            return null;
        }

        return teacher.toString();

    }

    //Método para mostrar el beneficio recaudado de todos los cursos restando el salario de los profesores
    public static double showProfitFromAllCourses(List<Course> courseList, List<Teacher> teacherList) {

        double totalEarned = 0;
        double totalSalaries = 0;

        for (Course course : courseList) {
            totalEarned += course.getMoneyEarned();
        }

        for (Teacher teacher : teacherList) {
            totalSalaries += teacher.getSalary();
        }

        return totalEarned - totalSalaries;

    }

    //Método de creación de profesores entrado por teclado los parámetros
    public static void createTeacher(List<Teacher> teachers) throws InputMismatchException {

        Scanner scanner = new Scanner(System.in);
        int numTeachers;

        System.out.println("How many teacher do you want?");
        while (true) {
            if (!scanner.hasNextInt()) {
                System.err.println("Introduce un valor numérico");
                scanner.next();
            } else {
                numTeachers = scanner.nextInt();
                break;
            }
        }

        for (int i = 0; i < numTeachers; i++) {
            scanner = new Scanner(System.in);
            System.out.println("Enter the Teacher's name " + (i + 1));
            String name = scanner.nextLine();
            System.out.println("Enter the Teacher's salary");
            while (true) {
                if (!scanner.hasNextDouble()) {
                    System.err.println("Introduce un valor numerico");
                    scanner.next();
                } else {
                    double salary = scanner.nextDouble();
                    teachers.add(new Teacher(name, salary));
                    break;
                }
            }
        }

    }

    //METODO CREACION DE CURSOS ENTRANDO POR TECLADO LOS PARAMETROS
    public static void createCourses(List<Course> courses) throws InputMismatchException {

        Scanner scanner = new Scanner(System.in);
        int numCourses;

        System.out.println("How many courses do you want?");
        while (true) {
            if (!scanner.hasNextInt()) {
                System.err.println("Introduce un valor numérico");
                scanner.next();
            } else {
                numCourses = scanner.nextInt();
                break;
            }
        }

        for (int i = 0; i < numCourses; i++) {
            scanner = new Scanner(System.in);
            System.out.println("Enter the Course's name " + (i + 1));
            String name = scanner.nextLine();
            System.out.println("Enter the price of this course ");
            while (true) {
                if (!scanner.hasNextDouble()) {
                    System.err.println("Introduce un valor numerico");
                    scanner.next();
                } else {
                    double price = scanner.nextDouble();
                    courses.add(new Course(name, price));
                    break;
                }
            }
        }
    }

    //METODO CREACION DE ESTUDIANTES ENTRANDO POR TECLADO LOS PARAMETROS
    public static void createStudent(List<Student> students) throws InputMismatchException {

        //todo: Raul: OJO! QUE PASA SI EL USUARIO METE UN NOMBRE VACÍO?

        Scanner scanner = new Scanner(System.in);
        int numStudents;
        System.out.println("How many students do you want?");
        while (true) {
            if (!scanner.hasNextInt()) {
                System.err.println("Introduce un valor numerico");
                scanner.next();
            } else {
                numStudents = scanner.nextInt();
                break;
            }
        }

        for (int i = 0; i < numStudents; i++) {
            scanner = new Scanner(System.in);
            System.out.println("Enter the Student's name " + (i + 1));
            String name = scanner.nextLine();
            System.out.println("Enter the adress of the Student ");
            String adress = scanner.nextLine();
            System.out.println("Enter the email of the Student ");
            String email = scanner.nextLine();

            students.add(new Student(name, adress, email));
        }
    }

}
