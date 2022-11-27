package ironschool;

import java.util.*;
import java.util.regex.Pattern;

// Clase donde se crearán todos los métodos de creación y gestión
public class Utilities {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Course> courseList;
    private static List<Student> studentList;
    private static List<Teacher> teacherList;
    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static void setUpSchool() {

        String name;

        try {

            System.out.println("Escribe un nombre para la escuela: ");
            while (true) {

                name = scanner.nextLine();

                if (name.isEmpty() || name.isBlank() || isNumeric(name)) {

                    System.out.println("Escribe un nombre válido!");

                } else {

                    break;

                }

            }

            // Método creación de profesores
            Utilities.createTeacher(teacherList);
            // Imprimimos la lista de profesores
            System.out.println(teacherList);

            // Método creación de cursos
            Utilities.createCourses(courseList);
            // Imprimimos la lista de cursos
            System.out.println(courseList);

            // Método de creación de estudiantes
            Utilities.createStudent(studentList);
            // Imprimimos la lista de estudiantes
            System.out.println(studentList);

        } catch (Exception e) {

            System.err.println(e);

        }
    }

    public static void callMenu() {

        Scanner scanner;
        String command;
        String subCommand1;
        String subCommand2 = "";
        String subCommand3 = "";
        String[] comandosSeparados;

        while (true) {

            // Imprimimos el menú inicial
            printMenu();
            // Controlamos que no inserte un número
            scanner = new Scanner(System.in);

            while (true) {

                if (scanner.hasNextInt()) {

                    System.err.println("Introduce un comando valido");
                    scanner.nextLine();

                } else {

                    command = scanner.nextLine().toUpperCase();
                    break;

                }

            }

            comandosSeparados = command.split(" ");
            subCommand1 = comandosSeparados[0];

            // Si ponemos 1, 2 o 3 palabras en el comando, esta bien, si no, no
            if (comandosSeparados.length <= 3 && comandosSeparados.length > 1) {

                switch (comandosSeparados.length) {
                    case 2:
                        subCommand2 = comandosSeparados[1];
                        break;
                    case 3:
                        subCommand2 = comandosSeparados[1];
                        subCommand3 = comandosSeparados[2];
                        break;
                    default:
                        System.err.println("Comprueba el comando introducido.");
                        callMenu();
                }

            }

            // Dependiendo del comando seleccionado haremos una funcionalidad u otra
            switch (subCommand1) {
                case "ENROLL":
                    enrollStudentIntoCourse(subCommand2, subCommand3);
                    break;
                case "ASSIGN":
                    assignTeacherIntoCourse(subCommand2, subCommand3);
                    break;
                case "SHOW":
                    switch (subCommand2) {
                        case "COURSES" -> System.out.println(showAllCourses());
                        case "STUDENTS" -> System.out.println(showAllStudents());
                        case "TEACHERS" -> System.out.println(showAllTeachers());
                        case "PROFIT" -> System.out.println(showProfitFromAllCourses());
                        default -> System.err.println("Introduce un comando valido");
                    }
                    break;
                case "LOOKUP":
                    switch (subCommand2) {
                        case "COURSE" -> System.out.println(lookupCourse(subCommand3));
                        case "STUDENT" -> System.out.println(lookupStudent(subCommand3));
                        case "TEACHER" -> System.out.println(lookupTeacher(subCommand3));
                        default -> System.err.println("Introduce un comando valido");
                    }
                    break;
                case "EXIT":
                    return;
                default:
                    System.err.println("Introduce un comando válido!");
                    break;
            }
        }

    }

    public static void printMenu() {
        System.out.println("""
                                
                    OPCIÓN 1: "ENROLL <ID_STUDENT> <ID_COURSE>" - Este comando nos permite asignar un estudiante a un curso.
                    OPCIÓN 2: "ENROLL <ID_TEACHER> <ID_COURSE>" - Este comando nos permite asignar un profesor a un curso.
                    OPCIÓN 3: "SHOW COURSES" - Este comando nos mostrará un listado con los cursos creados e información relacionada.
                    OPCIÓN 4: "LOOKUP COURSE <ID_COURSE>" - Este comando nos permitirá buscar un curso y mostrar información relacionada.
                    OPCIÓN 5: "SHOW STUDENTS" - Este comando nos mostrará un listado con los alumnos creados.
                    OPCIÓN 6: "LOOKUP STUDENT <ID_STUDENT>" - Este comando nos permitirá buscar a un estudiante y mostrar información relacionada.
                    OPCIÓN 7: "SHOW TEACHERS" - Este comando nos mostrará un listado con los profesores creados.
                    OPCIÓN 8: "LOOKUP TEACHER <ID_TEACHER>" - Este comando nos permitirá buscar a un profesor y mostrar información relacionada.
                    OPCIÓN 9: "SHOW PROFIT" - Este comando nos permitirá buscar a un estudiante y mostrar información relacionada.
                    OPCIÓN 10: "EXIT" - Este comando te permite salir del programa 
                              
                """);
    }

    // Método para inscribir un alumno a un curso mediante sus IDs
    public static void enrollStudentIntoCourse(String studentID, String courseID) {

        scanner = new Scanner(System.in);

        Student student = null;
        Course course = null;

        int option;

        //Buscamos si el ID del estudiante introducido existe en la lista de estudiantes
        for (Student studentElement : studentList) {

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
        for (Course courseElement : courseList) {

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

    // Método para asignar un profesor a un curso mediante sus ID
    public static void assignTeacherIntoCourse(String teacherID, String courseID) {

        scanner = new Scanner(System.in);

        Teacher teacher = null;
        Course course = null;

        //Buscamos si el ID del profesor introducido existe en la lista de profesores
        for (Teacher teacherElement : teacherList) {

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
        for (Course courseElement : courseList) {

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

    // Método para enseñar todos los cursos
    public static List<Course> showAllCourses() {
        return courseList;
    }

    // Método para enseñar toda la información de un curso mediante su ID
    public static String lookupCourse(String courseID) {

        Course course = null;

        //Buscamos si el ID del curso introducido existe en la lista de cursos
        for (Course courseElement : courseList) {

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

    // Método para enseñar todos los estudiantes
    public static List<Student> showAllStudents() {
        return studentList;
    }

    // Método para enseñar toda la información de un estudiante mediante su ID
    public static String lookupStudent(String studentID) {

        Student student = null;

        //Buscamos si el ID del curso introducido existe en la lista de cursos
        for (Student studentElement : studentList) {

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

    // Método para enseñar todos los profesores
    public static List<Teacher> showAllTeachers() {
        return teacherList;
    }

    // Método para enseñar toda la información de un profesor mediante su ID
    public static String lookupTeacher(String teacherID) {

        Teacher teacher = null;

        //Buscamos si el ID del curso introducido existe en la lista de cursos
        for (Teacher teacherElement : teacherList) {

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

    // Método para mostrar el beneficio recaudado de todos los cursos restando el salario de los profesores
    public static double showProfitFromAllCourses() {

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

    // Método creación de profesores entrando parámetros por teclado
    public static void createTeacher(List<Teacher> teachers) throws InputMismatchException {

        Scanner scanner = new Scanner(System.in);
        int numTeachers;
        String name;
        double salary;

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

            // Validamos que el usuario no meta un valor vacío
            while (true) {

                name = scanner.nextLine();

                if (name.isEmpty() || name.isBlank() || isNumeric(name)) {

                    System.out.println("Escribe un nombre válido!");

                } else {

                    break;

                }

            }

            System.out.println("Enter the Teacher's salary");

            // Validamos que el usuario meta un valor numérico
            while (true) {

                if (!scanner.hasNextDouble()) {

                    System.err.println("Introduce un valor numérico!");
                    scanner.next();

                } else {

                    salary = scanner.nextDouble();
                    // Añadimos los profesores a la lista
                    teachers.add(new Teacher(name, salary));
                    break;

                }

            }

        }

    }

    // Método creación de cursos entrado parámetros por teclado
    public static void createCourses(List<Course> courses) throws InputMismatchException {

        Scanner scanner = new Scanner(System.in);
        int numCourses;
        String name;
        double price;

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
            // Validamos que el usuario no meta un valor vacío
            while (true) {

                name = scanner.nextLine();

                if (name.isEmpty() || name.isBlank() || isNumeric(name)) {

                    System.out.println("Escribe un nombre válido!");

                } else {

                    break;

                }

            }

            System.out.println("Enter the price of this course ");

            while (true) {

                if (!scanner.hasNextDouble()) {

                    System.err.println("Introduce un valor numérico!");
                    scanner.next();

                } else {

                    price = scanner.nextDouble();
                    courses.add(new Course(name, price));
                    break;

                }

            }

        }

    }

    // Método creación de estudiante entrado parámetros por teclado
    public static void createStudent(List<Student> students) throws InputMismatchException {

        Scanner scanner = new Scanner(System.in);

        int numStudents;
        String name, address, email;

        System.out.println("How many students do you want?");
        while (true) {

            if (!scanner.hasNextInt()) {

                System.err.println("Introduce un valor numérico!");
                scanner.next();

            } else {

                numStudents = scanner.nextInt();
                break;

            }

        }

        for (int i = 0; i < numStudents; i++) {

            scanner = new Scanner(System.in);

            System.out.println("Enter the Student's name " + (i + 1));

            // Validamos que el usuario no meta un valor vacío
            while (true) {

                name = scanner.nextLine();

                if (name.isEmpty() || name.isBlank() || isNumeric(name)) {

                    System.out.println("Escribe un nombre válido!");

                } else {

                    break;

                }

            }

            System.out.println("Enter the address of the Student ");

            // Validamos que el usuario no meta un valor vacío
            while (true) {

                address = scanner.nextLine();

                if (address.isEmpty() || address.isBlank()) {

                    System.out.println("Escribe una dirección válido!");

                } else {

                    break;

                }

            }

            System.out.println("Enter the email of the Student ");
            // Validamos que el usuario no meta un valor vacío
            while (true) {

                email = scanner.nextLine();

                if (email.isEmpty() || email.isBlank()) {

                    System.out.println("Escribe un email válido!");

                } else {

                    break;

                }

            }

            students.add(new Student(name, address, email));

        }

    }

    public static boolean isNumeric(String strNum) {

        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();

    }

    // Método para inicializar los arrays
    public static void initializeArrays() {

        courseList = new ArrayList<>();
        teacherList = new ArrayList<>();
        studentList = new ArrayList<>();

    }

}
