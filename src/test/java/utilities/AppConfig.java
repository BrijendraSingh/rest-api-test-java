package utilities;

import java.io.*;
import java.util.Properties;

public class AppConfig {

    private static String CONFIG_FILE = "config.properties";

    public String getProperty(String propName) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(this.getPropertiesFile()));

        return properties.getProperty(propName);
    }

    public File getPropertiesFile() throws FileNotFoundException, IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CONFIG_FILE).getFile());

        return file;
    }
}
