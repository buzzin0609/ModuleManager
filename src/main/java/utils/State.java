package utils;

import java.util.HashMap;

public class State {
    private static State ourInstance = new State();

    public static State getInstance() {
        return ourInstance;
    }

    private HashMap<String, Object> internalState = new HashMap<String, Object>();

    private State() {
    }

    public void setState(HashMap<String, Object> partialState) {
        partialState.forEach((key, value) -> internalState.put(key, value));
    }

    public Object getValue(String key) {
        return internalState.get(key);
    }

    public void setValue(String key, Object value) {
        internalState.put(key, value);
    }
}
