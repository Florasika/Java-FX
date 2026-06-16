import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentController {

    private ObservableList<Student> students =
            FXCollections.observableArrayList();

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }

    public double calculateAverage() {

        if (students.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Student student : students) {
            total += student.getGrade();
        }

        return total / students.size();
    }
}