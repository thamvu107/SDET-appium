package utils;

import constants.WaitConstant;

public class WaitUtils {

    public static void waitForDOMStable() {
        try {
            Thread.sleep(WaitConstant.WAIT_DOM_READY_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
