package nl.hu.ipass.services;

/*In deze class worden de student-, huis- en slaapplekservices gemaakt.
* Via deze class kunnen de services elkaar onderling opvragen. */

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
