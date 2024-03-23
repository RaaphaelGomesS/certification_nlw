package com.rarwin.certification_nlw.entities;

import java.util.Objects;

public enum TechnologyIndicator {

    JAVA("Java"), NODE_JS("Node"), CSHARP("C#"), C("C"), PYTHON("Python");

    private final String name;

    TechnologyIndicator(String name) {
        this.name = name;
    }

    public static TechnologyIndicator getByTheName(String name) {

        for (TechnologyIndicator indicator : TechnologyIndicator.values()) {
            if (Objects.equals(indicator.name, name)) {
                return indicator;
            }
        }
        return null;
    }
}
