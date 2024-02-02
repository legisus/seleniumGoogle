package selenium.util;

import selenium.singleton.ConfigSource;

public class ConfigUtil {
    public static String getString(String s) {
        return ConfigSource.INSTANCE.config.getString(s);
    }
}
