import java.util.*;
abstract class Assignment {
    private String title;
    private int maxMarks;
    private String dueDate;
    public Assignment(String title, int maxMarks, String dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }
    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public abstract double calculateFinalMarks(double rawMarks, int daysLate);
    public abstract int getPenaltyPercentagePerDay();
}
class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, String dueDate) {
        super(title, maxMarks, dueDate);
    }
    public double calculateFinalMarks(double rawMarks, int daysLate) {
        double penalty = daysLate * 0.10 * rawMarks;
        return Math.max(0, rawMarks - penalty);
    }
    public int getPenaltyPercentagePerDay() { return 10; }
}
class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, String dueDate) {
        super(title, maxMarks, dueDate);
    }
    public double calculateFinalMarks(double rawMarks, int daysLate) {
        double penalty = daysLate * 0.20 * rawMarks;
        return Math.max(0, rawMarks - penalty);
    }
    public int getPenaltyPercentagePerDay() { return 20; }
}
class Student {
    private String name;
    public Student(String name) { this.name = name; }
    public String getName() { return name; }
}
class Submission {
    private Student student;
    private Assignment assignment;
    private String submissionDate;
    private int daysLate;
    private String status;
    private double finalMarks;
    public Submission(Student student, Assignment assignment, String submissionDate, int daysLate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.daysLate = daysLate;
        this.status = "Submitted";
        if (daysLate == 0) {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (on time). Status: Submitted.");
        } else {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + daysLate + " days late). Status: Submitted.");
        }
    }
    public Student getStudent() { return student; }
    public Assignment getAssignment() { return assignment; }
    public String getStatus() { return status; }
    public void grade(double rawMarks) {
        if (status.equals("Graded")) {
            System.out.println("Already graded.");
            return;
        }
        this.finalMarks = assignment.calculateFinalMarks(rawMarks, daysLate);
        this.status = "Graded";
        if (daysLate > 0) {
            int totalPenalty = daysLate * assignment.getPenaltyPercentagePerDay();
            System.out.println(student.getName() + " graded: " + (int)finalMarks + "/" + assignment.getMaxMarks() + " after " + totalPenalty + "% late penalty. Status: Graded.");
        } else {
            System.out.println(student.getName() + " graded: " + (int)finalMarks + "/" + assignment.getMaxMarks() + ". Status: Graded.");
        }
    }
    public void resubmit() {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
        } else {
            System.out.println("Resubmitted successfully.");
        }
    }
}
public class exp2 {
    public static void main(String[] args) {
        Assignment lab = new CodingAssignment("Linked List Lab", 50, "Mar 10");
        Assignment essay = new WrittenAssignment("Design Essay", 50, "Mar 12");
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Submission sub1 = new Submission(asha, lab, "Mar 10", 0);
        Submission sub2 = new Submission(ravi, essay, "Mar 14", 2);
        sub1.grade(45);
        sub2.grade(40);
        sub1.resubmit();
    }
}