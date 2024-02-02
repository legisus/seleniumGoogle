package selenium.util;

import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunCucumber {

    private static boolean serenityRestConfigured = false;

    @Before
    public static void initSerenityRest() {
        if (!serenityRestConfigured) {
            log.info("Initialize rest config");
            serenityRestConfigured = true;

        }
    }
}
