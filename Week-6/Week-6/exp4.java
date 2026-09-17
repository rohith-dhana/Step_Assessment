class GymMember {

    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println("Standard | Sessions: " + sessionsAttended);
    }
}

class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium | Trainer: " + trainerName
                + " | Sessions: " + getSessionsAttended());
    }
}

public class exp4 {

    public static String batchPrint(GymMember[] members) {

        StringBuilder result = new StringBuilder();

        for (GymMember member : members) {

            if (member instanceof PremiumMember) {

                PremiumMember premium = (PremiumMember) member;

                result.append("Premium | Trainer: ")
                      .append(premium.getTrainerName())
                      .append(" | Sessions: ")
                      .append(member.getSessionsAttended())
                      .append(" [Trainer via downcast: ")
                      .append(premium.getTrainerName())
                      .append("] | ");

            } else {

                result.append("Standard | Sessions: ")
                      .append(member.getSessionsAttended())
                      .append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember[] members = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        System.out.println(batchPrint(members));
    }
}