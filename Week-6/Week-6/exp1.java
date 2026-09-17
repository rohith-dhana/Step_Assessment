class GymMember {

    protected String memberId;
    protected int monthlyFee;

    private int sessionsAttended;


    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid Member ID");
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Monthly fee must be positive");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }
    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println(
            "Standard Member | Sessions: " + sessionsAttended
        );
    }
}


class PremiumMember extends GymMember {

    private String trainerName;

    // Constructor
    public PremiumMember(
        String memberId,
        int monthlyFee,
        String trainerName
    ) {

        // Call parent constructor
        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {

        System.out.println(
            "Premium Member | Trainer: "
            + trainerName
            + " | Sessions: "
            + getSessionsAttended()
        );
    }

    
    public String getTrainerName() {
        return trainerName;
    }
}


public class exp1 {

    
    public static String signUpBatch(
        String[] memberIds,
        int monthlyFee
    ) {

        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {

            try {

                GymMember member =
                    new GymMember(id, monthlyFee);

                signedUp++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        return "Signed Up: "
            + signedUp
            + " | Rejected: "
            + rejected;
    }


    public static void main(String[] args) {

      
        PremiumMember p =
            new PremiumMember(
                "MEM01",
                2000,
                "Coach Riya"
            );

      
        p.attendSession();
        p.attendSession();

        
        System.out.println(
            "Sessions Attended: "
            + p.getSessionsAttended()
        );


        String[] memberIds = {
            "MEM1",
            "GM1",
            "MEM2",
            " ",
            "MEM3"
        };

        String result =
            signUpBatch(memberIds, 1000);

        System.out.println(result);
    }
}