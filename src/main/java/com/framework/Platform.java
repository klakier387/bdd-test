package com.framework;

import java.util.HashSet;

public enum Platform {
    CHROME("chrome"),
    FIREFOX("firefox"),
    ANY("any");

    private String value;

    private Platform(String value) {
        this.value = value;
    }

    public static Platform fromString(String input) {
        for (Platform platform : Platform.values()) {
            if (platform.getValue().equals(input)) {
                return platform;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public boolean isWeb() {
        return new HashSet<Platform>() {
            private static final long serialVersionUID = 1L;

            {
                add(CHROME);
                add(FIREFOX);
            }
        }.contains(this);

    }
}
