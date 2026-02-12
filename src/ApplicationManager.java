import java.io.*;
import java.util.*;

public class ApplicationManager {

    private List<ServiceApplication> applications = new ArrayList<>();
    private static final String FILE_NAME = "applications.txt";

    public ApplicationManager() {
        loadFromFile();
    }

    public void addApplication(ServiceApplication app) {
        applications.add(app);
    }

    public ServiceApplication findById(int id)
            throws ApplicationNotFoundException {

        for (ServiceApplication app : applications) {
            if (app.getApplicationId() == id) {
                return app;
            }
        }
        throw new ApplicationNotFoundException("Application ID not found: " + id);
    }

    public void approveApplication(int id)
            throws ApplicationNotFoundException, InvalidStatusException {

        ServiceApplication app = findById(id);
        app.approve();
    }

    public void displayAll() {
        for (ServiceApplication app : applications) {
            System.out.println(app);
        }
    }

    public void saveToFile() {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (ServiceApplication app : applications) {
                writer.write(app.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String nationalId = parts[1];
                String name = parts[2];
                String serviceName = parts[3];
                double fee = Double.parseDouble(parts[4]);
                ServiceApplication.Status status =
                        ServiceApplication.Status.valueOf(parts[5]);

                Citizen citizen = new Citizen(nationalId, name);

                GovernmentService service;

                if (serviceName.equals("Birth Certificate Service")) {
                    service = new BirthCertificateService();
                } else {
                    service = new DrivingTestService();
                }

                ServiceApplication app =
                        new ServiceApplication(citizen, service );

                app.setApplicationId(id);
                app.setStatus(status);

                applications.add(app);
            }

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void generateRevenueReport() {

        double totalRevenue = 0;
        Map<String, Double> revenueByService = new HashMap<>();

        for (ServiceApplication app : applications) {

            if (app.getStatus() ==
                    ServiceApplication.Status.APPROVED) {

                totalRevenue += app.getService().getFee();

                String serviceName =
                        app.getService().getServiceName();

                revenueByService.put(serviceName,
                        revenueByService.getOrDefault(serviceName, 0.0)
                                + app.getService().getFee());
            }
        }

        System.out.println("----- Revenue Report -------");
        System.out.println("Total Revenue: " + totalRevenue);

        for (String service : revenueByService.keySet()) {
            System.out.println(service  + revenueByService.get(service));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("revenue_report.txt"))) {
            writer.write("------- Revenue Report -------\n");
            writer.write("Total Revenue: " + totalRevenue + "\n");
            for (String service : revenueByService.keySet()) {
                writer.write(service  + revenueByService.get(service) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing revenue report.");
        }
    }
}

