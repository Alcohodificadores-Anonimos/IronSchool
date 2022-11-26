import ironschool.Course;
import ironschool.Student;
import ironschool.Teacher;
import ironschool.Utilities;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UtilitiesTest {

    List<Course> courseList;
    List<Student> studentList;
    List<Teacher> teacherList;

    @Test
    void showAllCoursesTest() {

        courseList = Utilities.showAllCourses();

        assertEquals(0, courseList.size());

        courseList.add(new Course("Prueba", 300.0));

        assertEquals(1, courseList.size());

    }

    @Test
    void lookupCourseTest() {

        courseList = Utilities.showAllCourses();

        Course course = new Course("1", "Prueba", 300);

        courseList.add(course);

        assertEquals(course.toString(), Utilities.lookupCourse("1"));

    }

    @Test
    void lookupStudentTest() {

        studentList = Utilities.showAllStudents();

        Student student = new Student("1", "Prueba", "Calle prueba 123", "prueba@gmail.com");

        studentList.add(student);

        assertEquals(student.toString(), Utilities.lookupStudent("1"));

    }

    @Test
    void lookupTeacherTest() {

        teacherList = Utilities.showAllTeachers();

        Teacher teacher = new Teacher("1", "Prueba", 2000.0);

        teacherList.add(teacher);

        assertEquals(teacher.toString(), Utilities.lookupTeacher("1"));

    }

    @Test
    void showProfitFromAllCoursesTest() {

        //Prueba sin ningún beneficio de ningún curso ni ningún salario de ningún profesor
        assertEquals(0, Utilities.showProfitFromAllCourses());

        Teacher teacher = new Teacher("1", "Jaume", 2000.0);

        teacherList = Utilities.showAllTeachers(); //Asi instanciamos el teacherList

        teacherList.add(teacher);

        // Debe dar -2000 porque hay un profesor inscrito con un salario de 2000
        assertEquals(-2000, Utilities.showProfitFromAllCourses());

        Course course = new Course("1", "DAM", 500);

        courseList = Utilities.showAllCourses();

        courseList.add(course);

        // Debe dar -1500 porque ahora hemos añadido un curso por valor de 500
        assertEquals(-1500, Utilities.showProfitFromAllCourses());

    }

}
