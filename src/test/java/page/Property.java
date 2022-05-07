package page;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {

    public static String get(String key) {
        return setUpProperties("src/test/resources/xpath.properties").getProperty(key);
    }

    public static String getCredentials(String key) {
        return setUpProperties("src/test/resources/credentials.properties").getProperty(key);
    }

    /**
     * Setting up properties for getting values.
     *
     * @param fileLocation example: src/test/resources/test.properties.
     * @return properties with loaded {@link java.io.FileInputStream}.
     */
    private static Properties setUpProperties(String fileLocation) {
        Properties properties = new Properties ();
        try {
            FileInputStream fs = new FileInputStream(fileLocation);
            properties.load(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
