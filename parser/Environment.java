package parser;

import java.util.HashMap;

public class Environment {

    public HashMap<String, Integer> env;

    Environment() {
        env = new HashMap<>();
    }
    boolean valueCheck(String id) {
        if (env.get(id) == null) {
            return false;
        }
        return true;
    }
    int get(String id) {
        Integer i = env.get(id);
        if (i == null) {
            System.err.println("ERROR: Variable '" + id + "' does not exist.");
            System.exit(-1);
        }
        return i;
    }

    void put(String id, int val) {
        Integer i = val;
        env.put(id, i);
    }
}
