public class ServiceApplication {
    public enum Status{
        PENDING,APPROVED,REJECTED
    }
   private static int counter = 1;

    private int applicationId;
    private Citizen citizen;
    private GovernmentService service;
    private Status status;


public ServiceApplication(Citizen citizen ,GovernmentService service){
    this.applicationId = counter++;
    this.citizen = citizen;
    this.service = service;
    this.status = Status.PENDING;
}

public int getApplicationId(){
    return applicationId;
}
public Citizen getCitizen() {
    return citizen;

 }

 public GovernmentService getService() {
        return service;
    }
   public  Status  getStatus(){
    return status;
   }
public void approve () throws InvalidStatusException {
    if (!status.equals(Status.PENDING)) {
        throw new InvalidStatusException("cannot approve application in state :" + status);

    }
    status = Status.APPROVED;
}
public void reject () throws InvalidStatusException {
    if (!status.equals(Status.PENDING)) {
        throw new InvalidStatusException("cannot reject application in state :" + status);
    }
    status = Status.REJECTED;

}
    public void setApplicationId(int id) {
        this.applicationId = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static void setCounter(int value) {
        counter = value;
    }


    @Override
    public String toString() {
        return applicationId + "," +
                citizen.getNationalId() + "," +
                citizen.getName() + "," +
                service.getServiceName() + "," +
                service.getFee() + "," +
                status;
    }

}

