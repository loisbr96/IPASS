package nl.hu.ipass.services;

public class ServiceProvider {
    private static  StudentService  studentService =  new StudentService();
    private static HuisService huisService = new HuisService();
    private static SlaapplekService slaapplekService = new SlaapplekService();

    public static StudentService getStudentService() {
        return studentService;
    }

    public static HuisService getHuisService(){
        return huisService;
    }

    public static SlaapplekService getSlaapplekService(){
        return slaapplekService;
    }
}
