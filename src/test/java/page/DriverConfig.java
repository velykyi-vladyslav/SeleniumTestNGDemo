package page;

import java.io.File;

public class DriverConfig {
    static {
        System.setProperty("webdriver.chrome.driver", findFile());
    }

    static private String findFile() {
        String[] paths = {"", "bin/", "target/classes"};

        for (String path : paths) {
            if (new File(path + "chromedriver.exe").exists())
                return path + "chromedriver.exe";
        }
        return "";
    }
}
