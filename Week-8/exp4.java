import java.util.*;
interface MembershipPlan {
    String getName();
    double calculateFee(double baseRate);
}
class MonthlyPlan implements MembershipPlan {
    public String getName() { return "Monthly"; }
    public double calculateFee(double baseRate) { return baseRate; }
}
class QuarterlyPlan implements MembershipPlan {
    public String getName() { return "Quarterly"; }
    public double calculateFee(double baseRate) { return baseRate * 3 * 0.90; }
}
class AnnualPlan implements MembershipPlan {
    public String getName() { return "Annual"; }
    public double calculateFee(double baseRate) { return baseRate * 12 * 0.75; }
}
class Member {
    private String name;
    public Member(String name) { this.name = name; }
    public String getName() { return name; }
}
enum Status { ACTIVE, FROZEN, EXPIRED }
class Membership {
    private Member member;
    private MembershipPlan plan;
    private double fee;
    private Status status;
    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee(1000.0);
        this.status = Status.ACTIVE;
        System.out.printf("%s membership created for %s. Fee: ₹%,.2f. Status: Active.%n", plan.getName(), member.getName(), fee);
    }
    public void checkIn() {
        if (status == Status.ACTIVE) {
            System.out.println(member.getName() + " checked in successfully.");
        } else if (status == Status.FROZEN) {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is Frozen.");
        } else {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is Expired.");
        }
    }
    public void freeze() {
        if (status == Status.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
        } else {
            status = Status.FROZEN;
            System.out.println(member.getName() + "'s membership frozen. Status: Frozen.");
        }
    }
    public void unfreeze() {
        if (status == Status.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else {
            status = Status.ACTIVE;
            System.out.println(member.getName() + "'s membership unfrozen. Status: Active.");
        }
    }
    public void expire() {
        status = Status.EXPIRED;
        System.out.println(member.getName() + "'s membership expired. Status: Expired.");
    }
}
public class exp4 {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");
        Membership ashaMem = new Membership(asha, new QuarterlyPlan());
        Membership raviMem = new Membership(ravi, new MonthlyPlan());
        ashaMem.checkIn();
        ashaMem.freeze();
        ashaMem.checkIn();
        raviMem.expire();
        raviMem.freeze();
    }
}
