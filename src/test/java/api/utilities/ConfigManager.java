package api.utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    static {
        try {
            // Load config.properties
            FileInputStream configFis =
                    new FileInputStream("src/test/resources/config.properties");
            properties.load(configFis);

            // Load token.properties
            FileInputStream tokenFis =
                    new FileInputStream("src/test/resources/token.properties");
            properties.load(tokenFis);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties files");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
