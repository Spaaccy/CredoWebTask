package ge.credo.testautomation.data.enums;

import lombok.Getter;

@Getter
public enum SubmitButtonState {

    ENABLED(true),
    DISABLED(false);

    private final boolean enabled;

    SubmitButtonState(boolean enabled) {
        this.enabled = enabled;
    }
}

