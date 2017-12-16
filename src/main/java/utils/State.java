package utils;

import java.util.HashMap;
import java.util.Map;

public class State {
    private static State ourInstance = new State();

    public static State getInstance() {
        return ourInstance;
    }

    private HashMap<String, String> internalState = new HashMap<String, String>();

    private State() {
    }

    public void setState(HashMap<String, String> partialState) {
        partialState.forEach((key, value) -> internalState.put(key, value));
    }

    public String getValue(String key) {
        return internalState.get(key);
    }

    public void setValue(String key, String value) {
        internalState.put(key, value);
    }
}
