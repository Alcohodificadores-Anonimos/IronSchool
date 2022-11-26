package ironschool;

import java.util.*;

public class Utilities {

    //CLASE DONDE SE CREARAN TODOS LOS MÉTODOS DE CREACIÓN Y GESTIÓN

    private static Scanner scanner =  new Scanner(System.in);
    public static List<Course> courseList = new ArrayList<>();
    public static List<Student> studentList = new ArrayList<>();
    public static List<Teacher> teacherList = new ArrayList<>();

    public static void setUpSchool() {

        try {
            //--------Falta implementar bucle While(TRUE)
            System.out.println("Type a name for the school: ");
            String name = scanner.nextLine();

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

    public static void callMenu() {
        Scanner scanner;
        String command;
        String subCommand1 = "";
        String subCommand2 = "";
        String subCommand3 = "";
        boolean off = true;

        while(off){

            printMenu();
            //Controlamos que no inserte un numero
            scanner = new Scanner(System.in);
            while (true) {
                if (scanner.hasNextInt()) {
                    System.err.println("Introduce un comando valido");
                    scanner.nextLine();
                }else{
                    command = scanner.nextLine().toUpperCase();
                    break;
                }
            }
            /*ERRORES
            EJ:   (HOLA)(ENROLL11) si no pones dos espacios lo cual no rellena los subcommands. Controlar que command.split("").length sea = a 3
                   (ENROLL 1 1 1) te lo deja poner pero no utiliza ninguno a partir del segundo numero.
            */

            String[] comandosSeparados = command.split(" ");
            subCommand1 = comandosSeparados[0];

            // Si ponemos 1, 2 o 3 palabras en el comando, esta bien, si no, no
            if (comandosSeparados.length <= 3) {

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

            /* subCommand1 = command.split(" ")[0];
             subCommand2 = command.split(" ")[1];
             subCommand3 = command.split(" ")[2];*/

            switch (subCommand1) {
                case "ENROLL": //Enroll
                    enrollStudentIntoCourse(subCommand2, subCommand3);
                    break;
                case "ASSIGN": //Assign
                    assignTeacherIntoCourse(subCommand2,subCommand3);
                    break;
                case "SHOW": //Courses
                    switch (subCommand2) {
                        case "COURSES" -> System.out.println(showAllCourses());
                        case "STUDENTS" -> System.out.println(showAllStudents());
                        case "TEACHERS" -> System.out.println(showAllTeachers());
                        case "PROFIT" -> System.err.println(showProfitFromAllCourses(courseList,teacherList));
                        default -> System.err.println("Introduce un comando valido");
                    }
                    break;
                case "LOOKUP": //Course
                    switch (subCommand2) {
                        case "COURSE" -> System.out.println(lookupCourse(subCommand3));
                        case "STUDENT" -> System.out.println(lookupStudent(subCommand3));
                        case "TEACHER" -> System.out.println(lookupTeacher(subCommand3));
                        default -> System.err.println("Introduce un comando valido");
                    }
                    break;
                case "EXIT": //Profit
                    off = false;
                    break;
                default:
                    System.err.println("Introduce un comando valido");
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
                              OPCIÓN 7: "SHOW TEACHER" - Este comando nos mostrará un listado con los profesores creados.
                              OPCIÓN 8: "LOOKUP TEACHER <ID_TEACHER>" - Este comando nos permitirá buscar a un profesor y mostrar información relacionada.
                              OPCIÓN 9: "SHOW PROFIT" - Este comando nos permitirá buscar a un estudiante y mostrar información relacionada.
                              OPCIÓN 10: "EXIT" - Este comando te permite salir del programa 
                """);
    }

    //Método para inscribir un alumno a un curso mediante sus IDs
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

    //Método para asignar un profesor a un curso mediante sus IDs
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

    //SHOW COURSES: This command will display a list of all courses

    //Método para enseñar todos los cursos
    public static List<Course> showAllCourses() {
        return courseList;
    }

    //LOOKUP COURSE [COURSE_ID]:
    // This command will display the full details of the specified course

    //Método para enseñar toda la información de un curso mediante su ID
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

    //SHOW STUDENTS:
    // This command will display a list of all students

    //Método para enseñar todos los estudiantes
    public static List<Student> showAllStudents() {
        return studentList;
    }

    //LOOKUP STUDENT [STUDENT_ID]:
    // This command will display the full details of the specified student

    //Método para enseñar toda la información de un estudiante mediante su ID
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

    //SHOW TEACHERS:
    // This command will display a list of all teachers

    //Método para enseñar todos los profesores
    public static List<Teacher> showAllTeachers() {
        return teacherList;
    }

    //LOOKUP TEACHER [TEACHER_ID]:
    // This command will display the full details of the specified teacher

    //Método para enseñar toda la información de un profesor mediante su ID
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

    //SHOW PROFIT:
    // This command will calculate
    // (The total money earned from all courses) - (The sum of all the teachers’ salaries) and return the result

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

    //  ¡¡¡ ATENCIÓN !!!  Hacer metodos TRY CATCH para que el programa no crashee cuando en el Scanner metemos datos errones (Enter Int Output String)


    //METODO CREACION DE TEACHERS ENTRANDO POR TECLADO LOS PARAMETROS
    public static void createTeacher(List<Teacher> teachers) throws InputMismatchException {

        Scanner scanner = new Scanner(System.in);
        int numTeachers;


        System.out.println("How many teacher do you want?");
        while (true) {
            if (!scanner.hasNextInt()) {
                System.err.println("Introduce un valor numérico");
                scanner.next();
            }else{
                numTeachers = scanner.nextInt();
                break;
            }
        }

        for (int i = 0; i < numTeachers; i++) {
            scanner = new Scanner(System.in);
            System.out.println("Enter the Teacher's name " + (i + 1));
            String name = scanner.nextLine();
            System.out.println("Enter the Teacher's salary");
            while(true){
                if(!scanner.hasNextDouble()){
                    System.err.println("Introduce un valor numerico");
                    scanner.next();
                }else{
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
            }else{
                numCourses = scanner.nextInt();
                break;
            }
        }

        for (int i = 0; i < numCourses; i++) {
            scanner = new Scanner(System.in);
            System.out.println("Enter the Course's name " + (i+1));
            String name = scanner.nextLine();
            System.out.println("Enter the price of this course ");
            while (true){
                if(!scanner.hasNextDouble()){
                    System.err.println("Introduce un valor numerico");
                    scanner.next();
                }else{
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
        while (true){
            if(!scanner.hasNextInt()){
                System.err.println("Introduce un valor numerico");
                scanner.next();
            }else{
                numStudents = scanner.nextInt();
                break;
            }
        }

        for (int i = 0; i < numStudents; i++) {
            scanner = new Scanner(System.in);
            System.out.println("Enter the Student's name " +(i+1));
            String name = scanner.nextLine();
            System.out.println("Enter the adress of the Student ");
            String adress = scanner.nextLine();
            System.out.println("Enter the email of the Student ");
            String email = scanner.nextLine();

            students.add(new Student(name, adress, email));
        }
    }

    // TEMPORAL >>> TESTS HERE <<< method for functionalities to implement later on
    // TO BE DELETED ...
    public static void tempMethodToTestFunctionalities() {

        courseList = new ArrayList<>();
        teacherList = new ArrayList<>();
        studentList = new ArrayList<>();


        /*Course courseDAM = new Course("Defense Against the Dark Arts", 800);
        Course courseASIX = new Course("Potions", 700);


        Teacher teacherJaume = new Teacher("Albus Dumbledore", 2000);
        Teacher teacherAlex = new Teacher("Severus Snape", 1500);


        Student studentCristian = new Student("Harry Potter", "Godrick's Hollow", "harry.potter@emagic.com");
        Student studentXavi = new Student("Hermione Granger", "Maverick Street, 19", "hermione.ganger@emagic.com");
        Student studentManu = new Student("Ronald Weasley", "The Burrow, n/N", "ron.weasley@emagic.com");
        Student studentEdu = new Student("Draco Malfoy", "Malfoy Manor", "draco.malfoy@emagic.com");


        courseList = new ArrayList<>();
        teacherList = new ArrayList<>();
        studentList = new ArrayList<>();

        courseList.add(courseDAM);
        courseList.add(courseASIX);

        teacherList.add(teacherJaume);
        teacherList.add(teacherAlex);

        studentList.add(studentCristian);
        studentList.add(studentXavi);
        studentList.add(studentManu);
        studentList.add(studentEdu);
*/

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

}
