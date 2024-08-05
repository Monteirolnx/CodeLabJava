package Gatio.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusComanda {
    ABERTA("Aberta"),
    FECHADA("Fechada");

    private String value;

    StatusComanda(String value) {
        this.value = value;
    }

    @JsonCreator
    public static StatusComanda fromValue(String value) {
        for (StatusComanda status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}