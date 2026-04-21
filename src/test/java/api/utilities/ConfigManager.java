package api.utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties configProps = new Properties();
    private static Properties userTokenProps = new Properties();
    private static Properties adminTokenProps = new Properties();

    static {
        try {
            // config.properties
            FileInputStream configFis =
                    new FileInputStream("src/test/resources/config.properties");
            configProps.load(configFis);

            // user token
            FileInputStream userFis =
                    new FileInputStream("src/test/resources/token.properties");
            userTokenProps.load(userFis);

            // admin token
            FileInputStream adminFis =
                    new FileInputStream("src/test/resources/admintoken.properties");
            adminTokenProps.load(adminFis);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties files", e);
        }
    }

    //Base config
    public static String getProperty(String key) {
        return configProps.getProperty(key);
    }

    //USER token
    public static String getUserToken() {
        return userTokenProps.getProperty("access_token");
    }

    //ADMIN token
    public static String getAdminToken() {
        return adminTokenProps.getProperty("access_token");
    }
}
