package ironschool;

public class Course {
    private static int idCounter;
    private String courseId;
    private String name;
    private double price;
    private double moneyEarned;
    private Teacher teacher;

    public Course(String name, double price) {
        this.courseId = String.valueOf(idCounter++);
        this.name = name;
        this.price = price;
    }

    private Course(String courseId, String name, double price) {
        this.courseId = courseId;
        this.name = name;
        this.price = price;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    @Override
    public String toString() {

        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", moneyEarned=" + moneyEarned +
                ", teacher=" + teacher +
                '}';

    }

}
