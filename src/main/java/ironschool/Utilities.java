package ironschool;

import java.util.UUID;

public class Utilities {

    //CLASE DONDE SE CREARAN TODOS LOS MÉTODOS DE CREACIÓN Y GESTIÓN

    public static String generarIdRandom(){
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

    /*public static void enrollStudentIntoCourse(String studentID, String courseID) {



    }*/

    //ASSIGN [TEACHER_ID] [COURSE_ID]:
    // This command will help assign the teacher specified to the corresponding course

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
