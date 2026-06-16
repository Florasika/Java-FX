public class Student {

    private String name;
    private String subject;
    private double grade;

    public Student(String name, String subject, double grade) {
        this.name = name;
        this.subject = subject;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public double getGrade() {
        return grade;
    }
}