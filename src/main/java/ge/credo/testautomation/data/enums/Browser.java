package ge.credo.testautomation.data.enums;

import lombok.Getter;

@Getter
public enum Browser {

    CHROME("chrome"),
    EDGE("edge");

    private final String name;

    Browser(String name) {
        this.name = name;
    }

    public static Browser fromString(String name) {
        for (Browser browser : values()) {
            if (browser.name.equalsIgnoreCase(name)) {
                return browser;
            }
        }
        throw new IllegalArgumentException("Unsupported browser: " + name);
    }
}

