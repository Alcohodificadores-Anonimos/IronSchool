package ironschool;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Utilities {

    //CLASE DONDE SE CREARAN TODOS LOS MÉTODOS DE CREACIÓN Y GESTIÓN

    private static Scanner scanner;

    //todo: MANU: Yo creare metodo crear teacher

    //todo: RAUL: Cuando se cree un alumno, en el construcor, poner que se añada a la lista de alumnos para que ya
    // pertenezca a la lista automaticamente

    public static String generarIdRandom() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    //ENROLL [STUDENT_ID] [COURSE_ID]:
    // This command will help enroll the student specified in the corresponding course.
    // While also updating the money_earned of that course based on its price

    //Método para inscribir un alumno a un curso
    public static void enrollStudentIntoCourse(Student student, Course course) {

        //todo: Mirar crear una clase escuela

        //todo: Mirar crear una clase escuela y recorrerlo con Map

        //Comprobamos que el estudiante no esté ya inscrito en un curso
        if (student.getCourse() == null) {

            //Le asignamos el curso al estudiante
            student.setCourse(course);

            //Actualizamos el dinero que tenga del curso sumándole lo que ha costado el curso
            course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());

        } else {

            throw new IllegalArgumentException("El estudiante ya esta inscrito en un curso!");

        }

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

        //todo: preguntar a los profes si las listas tienen que ser staticas y publicas para poder acceder desde la clase Utilities

        //Buscamos si el ID del estudiante introducido existe en la lista de estudiantes
        for (Student studentElement : Main.studentList) {
            if (studentElement.getStudentId().equals(studentID)) {
                student = studentElement;
                break;
            }
        }
        //Buscamos si el ID del curso introducido existe en la lista de cursos
        for (Course courseElement : Main.courseList) {
            if (courseElement.getCourseId().equals(courseID)) {
                course = courseElement;
                break;
            }
        }

        //Si el alumno no existe o el ID está mal escrito, lanzamos una exception
        if (student == null) {
            throw new IllegalArgumentException("No existe el alumno con ID (" + studentID + ") o no esta mal escrito");
        }

        //Si el curso no existe o el ID está mal escrito, lanzamos una exception
        if (course == null) {
            throw new IllegalArgumentException("No existe el curso con ID (" + courseID + ") o no esta mal escrito");
        }

        //Comprobamos que el estudiante no esté ya inscrito en un curso
        if (student.getCourse() != null) {

            //Si el curso que estemos añadiendo es el mismo que ya tiene asignado, saltamos el proceso y no lo cobramos
            if (student.getCourse().getName().equals(course.getName())) {
                System.out.println("\n\t¡ATENCIÓN!\n\tEl estudiante ya estába inscrito en el curso (" + course.getName() +
                        "), volviendo al menú.");
                return;
            }

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

                    if (option == 1 || option == 2) {
                        break;
                    } else {
                        System.out.println("Opción incorrecta! (1.Si | 2.No)");
                    }

                }

            }

            //Si es 1, actualizamos el curso, si no (es 2), no lo actualizamos
            if (option == 1) {

                //Le asignamos el nuevo curso al estudiante
                student.setCourse(course);

                //Actualizamos el dinero que tenga del curso sumándole lo que ha costado el curso
                course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());

                System.out.println("\n\tCurso actualizado! Volviendo al menú principal.");
                return;

            } else {

                System.out.println("\n\tCurso no actualizado. Volviendo al menú principal.");
                return;

            }

        }

        //Le asignamos el curso al estudiante
        student.setCourse(course);

        //Actualizamos el dinero que tenga del curso sumándole lo que ha costado el curso
        course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());

        System.out.println("\n\tCurso nuevo añadido! Volviendo al menú principal.");

    }

    //ASSIGN [TEACHER_ID] [COURSE_ID]:
    // This command will help assign the teacher specified to the corresponding course

    //Método para asignar un profesor a un curso mediante sus IDs
    public static void assigntTeacherIntoCourse(String teacherID, String courseID) {



    }

    //SHOW COURSES: This command will display a list of all courses

    //LOOKUP COURSE [COURSE_ID]:
    // This command will display the full details of the specified course

    //SHOW STUDENTS:
    // This command will display a list of all students

    //LOOKUP STUDENT [STUDENT_ID]:
    // This command will display the full details of the specified student

    //SHOW TEACHERS:
    // This command will display a list of all teachers

    //LOOKUP TEACHER [TEACHER_ID]:
    // This command will display the full details of the specified teacher

    //SHOW PROFIT:
    // This command will calculate
    // (The total money earned from all courses) - (The sum of all the teachers’ salaries) and return the result

}
