package ironschool;

public class Teacher {
    private static int idCounter;
    private String teacherId;
    private String name;
    private double salary;

    public Teacher(String name, double salary) {
        this.teacherId = String.valueOf(idCounter++);
        this.name = name;
        this.salary = salary;
    }

    // todo: RAUL: OJO BORRAR ESTO DESPUES DE PRUEBAS
    public Teacher(String teacherId, String name, double salary) {
        this.teacherId = teacherId;
        this.name = name;
        this.salary = salary;
    }

    private String getIdCounterToString() {
        return Integer.toString(idCounter++);
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +'\''+
                '}';
    }

}