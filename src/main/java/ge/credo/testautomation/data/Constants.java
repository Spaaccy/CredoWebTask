package ge.credo.testautomation.data;

public final class Constants {

    // URLs
    public static final String
            BASE_URL = "https://mycredo.ge",
            LANDING_PAGE_URL = BASE_URL+"/landing/main/auth";
    // Timeouts (seconds)
    public static final int
            PAGE_LOAD_TIMEOUT = 30,
            EXPLICIT_WAIT_TIMEOUT = 5;
    // Random string lengths
    public static final int
            USERNAME_LENGTH = 8,
            PASSWORD_LENGTH = 10;
    // Browser
    public static final String DEFAULT_BROWSER = "chrome";
    // Browser arguments
    public static final String
            CHROME_MAXIMIZED = "--start-maximized",
            CHROME_DISABLE_NOTIFICATIONS = "--disable-notifications",
            CHROME_REMOTE_ALLOW_ORIGINS = "--remote-allow-origins=*";
    // UI text
    public static final String
            ACTIVE_HEADER_TEXT_GEO = "მთავარი",
            ACTIVE_CLASS = "active";
    // Input attributes
    public static final String
            INPUT_TYPE_TEXT = "text";
    // JavaScript
    public static final String
            JS_CLEAR_INPUT = "arguments[0].value=''; arguments[0].dispatchEvent(new Event('input'));";

    private Constants() {
    }
}

