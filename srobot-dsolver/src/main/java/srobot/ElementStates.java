package srobot;

import java.util.HashMap;
import java.util.Map;

public class ElementStates {
    private final Map<Element, ElementState> elementStates = new HashMap<>();

    public ElementStates(Elements elements) {
        elements.asStream().forEach(
                element -> {
                    elementStates.put(element, new ElementState());
                }
        );

    }
}
