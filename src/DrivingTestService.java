public class DrivingTestService extends GovernmentService {

    public DrivingTestService(){
        super("Driving Test Service", 10000);

    }
    @Override
    public String getServiceDetails(){
        return "Driving test application ";
    }
}
