package ironschool;

public class Student {
    private static int idCounter;
    private String studentId;
    private String name;
    private String address;
    private String email;
    private Course course;

    public Student(String name, String address, String email) {
        this.studentId = String.valueOf(idCounter++);
        this.name = name;
        this.address = address;
        this.email = email;
    }

    // todo: RAUL: OJO BORRAR ESTO TRAS PRUEBAS
    public Student(String studentId, String name, String address, String email) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    private String getIdCounterToString() {
        return Integer.toString(idCounter++);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studenId) {
        this.studentId = studenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {

        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", course=" + course +
                '}';

    }

}
