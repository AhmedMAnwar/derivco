package propertyfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
/*
 * Class presenting properties file loader 
 */
public final class PropertyFileLoader {
    private final static String urlPath = "/config.properties";
    private final Properties properties;

    public PropertyFileLoader() throws IOException {
        URL url = PropertyFileLoader.class.getResource(urlPath);
        InputStream inputStream = new FileInputStream(url.getPath());
        this.properties = new Properties();
        properties.load(inputStream);
    }

    public String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }

    public String getSearchUrl() {
        return properties.getProperty("SEARCH_URL");
    }

    public String getLookUpUrl() {
        return properties.getProperty("LOOKUP_URL");
    }
}
