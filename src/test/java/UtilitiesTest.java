import ironschool.Course;
import ironschool.Utilities;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UtilitiesTest {

    List<Course> courseList;

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

        Course course = new Course(1, "Prueba", 300);

        courseList.add(course);

        // assertNotNull(Utilities.lookupCourse(String.valueOf(1)));

    }

}
