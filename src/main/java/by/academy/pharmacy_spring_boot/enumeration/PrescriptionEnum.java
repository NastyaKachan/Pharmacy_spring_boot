package by.academy.pharmacy_spring_boot.enumeration;

import lombok.Getter;

@Getter
public enum PrescriptionEnum {
    RECEIPT("ПО РЕЦЕПТУ"),
    NO_RECEIPT("БЕЗ РЕЦЕПТА");

    private final String value;

    PrescriptionEnum(String name) {
        this.value = name;
    }
}
