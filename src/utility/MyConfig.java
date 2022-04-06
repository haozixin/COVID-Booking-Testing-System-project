package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyConfig {
    public static Properties myProp = new Properties();
    public static InputStream myResource = MyConfig.class.getResourceAsStream("/myConfig.properties");
    static {
        try {
            myProp.load(myResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMyConf(String props) {

        return myProp.getProperty(props);
    }

}