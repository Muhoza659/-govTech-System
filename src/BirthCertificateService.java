public class BirthCertificateService extends GovernmentService {

     public BirthCertificateService(){
         super("BirthCertificate",7000);
     }

     @Override
     public String getServiceDetails(){
         return "Issuance  of official birth certificate ";
     }

}
