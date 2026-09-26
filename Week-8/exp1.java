import java.util.*;
abstract class WashType {
    private String name;
    private int durationMinutes;
    private double charge;
    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }
    public String getName() { return name; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getCharge() { return charge; }
}
class QuickWash extends WashType {
    public QuickWash() { super("Quick", 30, 20.0); }
}
class NormalWash extends WashType {
    public NormalWash() { super("Normal", 45, 30.0); }
}
class HeavyWash extends WashType {
    public HeavyWash() { super("Heavy", 60, 45.0); }
}
class Student {
    private String name;
    public Student(String name) { this.name = name; }
    public String getName() { return name; }
}
class WashingMachine {
    private String id;
    private boolean isBusy;
    public WashingMachine(String id) {
        this.id = id;
        this.isBusy = false;
    }
    public String getId() { return id; }
    public boolean isBusy() { return isBusy; }
    public void setBusy(boolean busy) { this.isBusy = busy; }
}
class LaundrySystem {
    public void startWash(Student student, WashingMachine machine, WashType washType) {
        if (machine.isBusy()) {
            System.out.println("Machine " + machine.getId() + " is currently busy.");
            return;
        }
        machine.setBusy(true);
        System.out.printf("%s wash started on %s for %s (%d min). Charge: %.2f.%n",
                washType.getName(), machine.getId(), student.getName(), washType.getDurationMinutes(), washType.getCharge());
    }
    public void completeWash(WashingMachine machine) {
        machine.setBusy(false);
        System.out.println(machine.getId() + " cycle completed. " + machine.getId() + " is now free.");
    }
}
public class exp1 {
    public static void main(String[] args) {
        LaundrySystem system = new LaundrySystem();
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        system.startWash(asha, m1, new QuickWash());
        system.startWash(ravi, m1, new HeavyWash());
        system.startWash(ravi, m2, new HeavyWash());
        system.completeWash(m1);
        system.startWash(neha, m1, new NormalWash());
    }
}