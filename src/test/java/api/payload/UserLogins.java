package api.payload;

public class UserLogins {

    private static final String USERNAME = "Ismailding@progresif.com";//Another user izzulding@gmail.com
    private static final String PASSWORD = "Progresif@123";
    
    private static final String ADMINUSER = "dingadmin@progresif.com";
    private static final String ADMINPASSWORD = "NeosoftAdmin@123";

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }
    
    public static String getAdminUsername() {
        return ADMINUSER;
    }

    public static String getAdminPassword() {
        return ADMINPASSWORD;
    }
}
