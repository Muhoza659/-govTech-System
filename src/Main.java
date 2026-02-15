public class Main {

    public static void main(String[] args) {

        ApplicationManager manager = new ApplicationManager();

        Citizen c1 = new Citizen("1199900012345678", "Jane");
        Citizen c2 = new Citizen("1198800012345678", "Peter");


        GovernmentService birth = new BirthCertificateService();
        GovernmentService driving = new DrivingTestService();

        ServiceApplication app1 =
                new ServiceApplication(c1, birth);

        ServiceApplication app2 =
                new ServiceApplication(c2, driving);

        manager.addApplication(app1);
        manager.addApplication(app2);

        try {
            manager.approveApplication(app1.getApplicationId());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        manager.displayAll();

        manager.saveToFile();
        manager.generateRevenueReport();

        System.out.println("System finished successfully.");
    }
}
