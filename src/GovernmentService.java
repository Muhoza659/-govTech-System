public abstract class GovernmentService {
    private String serviceName;
    private double fee;

    public GovernmentService(String serviceName, double fee){
        this.serviceName =serviceName;
        this.fee = fee ;
    }

    public String getServiceName(){
        return serviceName;
    }

    public double getFee() {
        return fee;
    }
    public abstract String getServiceDetails();
}
