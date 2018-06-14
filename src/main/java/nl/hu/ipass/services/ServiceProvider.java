package nl.hu.ipass.services;

public class ServiceProvider {
    private static  StudentService  studentService =  new StudentService();
    private static HuisService huisService = new HuisService();

    public static StudentService getStudentService() {
        return studentService;
    }

    public static HuisService getHuisService(){
        return huisService;
    }
}
