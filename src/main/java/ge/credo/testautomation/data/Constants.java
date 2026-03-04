package ge.credo.testautomation.data;

import java.util.List;

public final class Constants {

    public static final class Urls {
        public static final String
                BASE_URL = "https://mycredo.ge",
                LANDING_PAGE_URL = BASE_URL + "/landing/main/auth";
    }

    public static final class Timeouts {
        public static final int
                PAGE_LOAD_TIMEOUT = 30,
                EXPLICIT_WAIT_TIMEOUT = 5;
    }

    public static final class Random {
        public static final int
                USERNAME_LENGTH = 8,
                PASSWORD_LENGTH = 10;
    }

    public static final class Browser {
        public static final String
                DEFAULT_BROWSER = "chrome",
                CHROME_MAXIMIZED = "--start-maximized",
                CHROME_DISABLE_NOTIFICATIONS = "--disable-notifications",
                CHROME_REMOTE_ALLOW_ORIGINS = "--remote-allow-origins=*";
        public static final List<String> BROWSER_ARGS = List.of(
                CHROME_MAXIMIZED, CHROME_DISABLE_NOTIFICATIONS, CHROME_REMOTE_ALLOW_ORIGINS
        );
    }

    public static final class UI {
        public static final String
                ACTIVE_HEADER_TEXT_GEO = "მთავარი",
                ACTIVE_CLASS = "active",
                INPUT_TYPE_TEXT = "text";
    }

    public static final class JavaScript {
        public static final String JS_CLEAR_INPUT = "arguments[0].value=''; arguments[0].dispatchEvent(new Event('input'));";
    }
}
